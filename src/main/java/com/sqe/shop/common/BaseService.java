package com.sqe.shop.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

public class BaseService {

	/**
	 * 创建文件目录
	 * @param File
	 */
	public void checkDir(File dir) {
        if(!dir.exists()){  
        	dir.mkdirs();  
        }
	}
	
	/**
	 * 创建文件目录
	 * @param folderPath
	 */
	public void checkDir(String folderPath) {
		File dir = new File(folderPath);
        if(!dir.exists()){  
        	dir.mkdirs();  
        }
	}
	
	public boolean checkFile(MultipartFile file, String targetFileFormate){
		String fileName = file.getOriginalFilename();
		String[] arr= fileName.split(".");
		if(arr.length<2){
			return false;
		}
		if(!arr[1].toLowerCase().equals(targetFileFormate)){
			return false;
		}
		return true;
	}
	
	/**
	 * 下载文件
	 * @param response
	 * @param csvFilePath
	 *			  文件路径
	 * @param fileName
	 *			  文件名称
	 * @throws IOException
	 */
	public static void downloadFile(HttpServletResponse response, String filePath) throws IOException {
		try {
            // path是指欲下载的文件的路径。
            File file = new File(filePath);
            // 取得文件名。
            String filename = file.getName();
            // 取得文件的后缀名。
            String ext = filename.substring(filename.lastIndexOf(".") + 1).toUpperCase();

            // 以流的形式下载文件。
            InputStream fis = new FileInputStream(file);
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
	}

	/**
	 * 删除该目录filePath下的所有文件
	 * @param filePath
	 *			文件目录路径
	 */
	public static void deleteFiles(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					files[i].delete();
				}
			}
		}
	}

	/**
	 * 删除单个文件
	 * @param filePath
	 *		 文件目录路径
	 * @param fileName
	 *		 文件名称
	 */
	public static void deleteFile(String filePath, String fileName) {
		File file = new File(filePath);
		if (file.exists()) {
			File[] files = file.listFiles();
			for (int i = 0; i < files.length; i++) {
				if (files[i].isFile()) {
					if (files[i].getName().equals(fileName)) {
						files[i].delete();
						return;
					}
				}
			}
		}
	}
	
}
