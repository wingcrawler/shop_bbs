package com.sqe.shop.service.file;

import java.io.File;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.sqe.shop.common.BaseService;

@Component
public class FileUploadService  extends BaseService {

	public void uploadAttachFile(MultipartFile file, String uploadPath) {
		File dir = new File(uploadPath, file.getOriginalFilename());
		checkDir(dir);
        try {  
            file.transferTo(dir);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
	}

}
