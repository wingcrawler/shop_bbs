package com.sqe.shop.file.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.tomcat.util.http.fileupload.FileItemIterator;
import org.apache.tomcat.util.http.fileupload.FileItemStream;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.util.Streams;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

@Component
public class FileUploadService {

	public void uploadAttachFile(MultipartFile file, String uploadPath) {
		File dir = new File(uploadPath, file.getOriginalFilename());
		checkDir(dir);
        try {  
            file.transferTo(dir);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}

	public String  uploadImage(MultipartFile attachFile, String uploadPath) {
		if(attachFile==null){
			return "";
		}
		
		File dir = new File(uploadPath);
		checkDir(dir);
		
		try {
			if(attachFile!=null){
				String fileName = attachFile.getOriginalFilename();
				if (StringUtils.isNotBlank(fileName)) {
					Pattern reg = Pattern.compile("[.]jpg|JPG|png|PNG|jpeg|JPEG|gif|GIF$");
					Matcher matcher = reg.matcher(fileName);
					if (!matcher.find()) {
						return "";
					}
					String ftype = matcher.group();
					fileName = new Date().getTime() + ftype;
					uploadPath = uploadPath + "/" + fileName;
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
	
	private void checkDir(File dir) {
        if(!dir.exists()){  
        	dir.mkdirs();  
        }
	}
	
}
