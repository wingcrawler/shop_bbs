package com.sqe.shop.service.file;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.util.Streams;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sqe.shop.common.BaseService;
import com.sqe.shop.model.User;
import com.sqe.shop.service.UserService;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.MD5Util;
import com.sqe.shop.util.RegularUtil;

@Component
public class TxtService extends BaseService {
	
	
	@Autowired
	private UserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(TxtService.class);

	public void exportUser(HttpServletRequest request, HttpServletResponse response, List<User> dataList, String filePath) {
		checkDir(filePath);
		String dateStr = DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1);
		String fileName = "user_"+dateStr+".txt";
		File file = new File(filePath + fileName);
		
		FileOutputStream out=null; 
        OutputStreamWriter osw=null;  
        BufferedWriter bw=null;  
        try {  
        	if (!file.exists()) { 
        		file.createNewFile(); 
    		}
        	
        	out = new FileOutputStream(file);
            osw = new OutputStreamWriter(out);  
            bw =new BufferedWriter(osw);  
            //head  
            bw.append("用户名/username | 手机/phone | 邮箱/mail | 注册时间/createTime | 角色/role | 状态/status | 地址/address |").append("\r\n");
            //循环数据  
            if(dataList!=null && !dataList.isEmpty()){  
                for(User user : dataList){  
                	bw.append(user.getUsername()).append(" | ");
                	bw.append(user.getUserPhone()).append(" | ");
                	bw.append(user.getUserMail()).append(" | ");
                	bw.append(DateUtil.dateToString(user.getCreateTime(), DateUtil.DATETIME_FORMATE_2)).append(" | ");
                	bw.append(user.getUserRole().toString()).append(" | ");
                	bw.append(user.getUserStatus().toString()).append(" | ");
                	bw.append(user.getUserAddress()).append(" | ");
                	bw.append("\r\n");
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            if(bw!=null){  
                try {
                	bw.flush();
                    bw.close();  
                    bw=null;  
                    downloadFile(response, filePath + fileName);
                    deleteFile(filePath, fileName);
                } catch (IOException e) {  
                    e.printStackTrace();  
                }   
            }  
            if(osw!=null){  
                try {  
                    osw.close();  
                    osw=null;  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }   
            }  
            if(out!=null){  
                try {  
                    out.close();  
                    out=null;  
                } catch (IOException e) {  
                    e.printStackTrace();  
                }   
            }  
        }  
          
	}

	public String uploadTxtFile(MultipartFile attachFile, String uploadPath){
		try {
			if(attachFile!=null){
				checkDir(uploadPath);
				String fileName = attachFile.getOriginalFilename();
				if (StringUtils.isNotBlank(fileName)) {
					Pattern reg = Pattern.compile("[.]txt|TXT$");
					Matcher matcher = reg.matcher(fileName);
					if (!matcher.find()) {
						return "";
					}
					String ftype = matcher.group();
					fileName = new Date().getTime() + ftype;
					uploadPath = uploadPath + fileName;
					BufferedInputStream in = new BufferedInputStream(attachFile.getInputStream());
					FileOutputStream a = new FileOutputStream(new File(uploadPath));
					BufferedOutputStream output = new BufferedOutputStream(a);
					Streams.copy(in, output, true);
					return fileName;
				}
			}
		} catch (Exception e) {
			return "";
		}
		
		return "";
	}

	public String userImport(String fileName) {
		List<User> list = new ArrayList<User>();
		try {
			File file=new File(fileName);
			if(file.isFile() && file.exists()){ //判断文件是否存在
				InputStreamReader read = new InputStreamReader(new FileInputStream(file),"utf-8");//考虑到编码格式
				BufferedReader bufferedReader = new BufferedReader(read);
				String lineTxt = null;
				int totalCount = -1;
				String username;
				String role;
				String phone;
				String mail;
				String passwd;
				MD5Util encoderMd5 = new MD5Util(MD5Util.SALT, "MD5");
				String encode = encoderMd5.encode("123456");
				while((lineTxt = bufferedReader.readLine()) != null){
					totalCount++;
					if(totalCount==0){
						continue;
					}
					if(StringUtils.isBlank(lineTxt)){
						break;
					}
					String[] arr = lineTxt.split("\\|");
					username = arr[0].trim();
					role = arr[1].trim();
					if(!RegularUtil.isNumeric(role)){
						continue;
					}
					phone = arr[2].trim();
					mail = arr[3].trim();
					passwd=arr[4].trim();
					if(StringUtils.isBlank(username)||StringUtils.isBlank(role)||StringUtils.isBlank(mail)){
						continue;
					}
					User user = new User();
					user.setUsername(username);
					user.setUserRole(Long.valueOf(role));
					user.setUserMail(mail);
					user.setPassword(encoderMd5.encode(passwd));
					user.setUserStatus(1);
					user.setUserPhone(phone);
					user.setCreateTime(new Date());
					
					//是否已存在用户名
					List<User> existUsers = userService.findOnlyByName(username);
					if(existUsers==null || existUsers.isEmpty()){
						list.add(user);	
						logger.info("{}",username);	
					}
					
				}
				read.close();
				
				//导入
				int successCount = userService.batchInsert(list);
				StringBuffer sb = new StringBuffer();
				sb.append("总数/total count:").append(totalCount);
				sb.append("\r\n");
				sb.append("有效数/effective count:").append(successCount);
				deleteFile(fileName);
				return sb.toString();
			}else{
				logger.error("找不到指定的文件");
			}
		} catch (Exception e) {
			logger.error("读取文件内容出错");
			e.printStackTrace();
		}
		return "";
	}
	
}
