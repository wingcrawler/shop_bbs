package com.sqe.shop.service.file;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sqe.shop.common.BaseService;
import com.sqe.shop.util.DateUtil;
import com.sqe.shop.util.PropertiesUtil;

@Component
public class ImageFileService extends BaseService {

	private static final Logger logger = LoggerFactory.getLogger(ImageFileService.class);
	
	public Map<String, Object> uploadImage(MultipartFile attachFile, String uploadPath) {
		if(attachFile==null){
			return responseError(-1, "error_no_file");
		}
		File dir = new File(uploadPath);
		checkDir(dir);
		
		try {
			if(attachFile!=null){
				String fileName = attachFile.getOriginalFilename();
				if (StringUtils.isNotBlank(fileName)) {
					
					if(attachFile.getSize()>IMG_MAXI_SIZE){
						return responseError(-1, "error_img_out_of_rang");
					}
					
					Pattern reg = Pattern.compile("[.]jpg|JPG|png|PNG|jpeg|JPEG|gif|GIF$");
					Matcher matcher = reg.matcher(fileName);
					if (!matcher.find()) {
						return responseError(-1, "error_img_formate");
					}
					String ftype = matcher.group();
					fileName = new Date().getTime() + ftype;
					uploadPath = uploadPath + fileName;
					BufferedInputStream in = new BufferedInputStream(attachFile.getInputStream());
					FileOutputStream a = new FileOutputStream(new File(uploadPath));
					BufferedOutputStream output = new BufferedOutputStream(a);
					Streams.copy(in, output, true);
					return responseOK1(fileName);
				}
			}
		} catch (Exception e) {
			logger.error("upload image exception："+e.getMessage());
			return responseError1(-1, e.getMessage());
		}
		return responseError(-1, "error_unknow");
	}

	public Map<String, Object> uploadImage(MultipartFile attachFile) {
		String uploadPath = PropertiesUtil.get("upload_path"); 
    	uploadPath += DateUtil.dateToString(new Date(), DateUtil.DATE_FORMATE_1)+"//";
		return this.uploadImage(attachFile, uploadPath);
	}
	
}
