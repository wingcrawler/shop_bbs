package com.sqe.shop.file.service;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.sqe.shop.common.BaseService;
import com.sqe.shop.model.User;
import com.sqe.shop.util.DateUtil;

@Component
public class TxtService extends BaseService {

	public void exportUser(HttpServletRequest request, HttpServletResponse response, List<User> dataList) {
        String filePath = "file/txt/";
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

	
	
}
