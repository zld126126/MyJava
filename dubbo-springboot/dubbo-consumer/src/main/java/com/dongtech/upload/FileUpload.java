package com.dongtech.upload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import com.dongtech.constants.Constants;
import com.dongtech.util.RandomUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.web.multipart.MultipartFile;


/**
 * 文件处理处理类
 * 
 * @author 东宝
 * 
 */
public class FileUpload {
	
	/**
	 * 文件上传处理
	 * 
	 * @param file
	 * @return
	 */
	public static String writeUploadFile(MultipartFile file) {
		String datepath = DateFormatUtils.format(System.currentTimeMillis(), "yyyy/MM/dd" ) + "/" + RandomUtils.getRandom(100);
		String filename = file.getOriginalFilename();
		String retpath = "/" + datepath;

		String realpath = Constants.FILE_ROOT + retpath;
		File fileDir = new File(realpath);
		if (!fileDir.exists())
			fileDir.mkdirs();

		String extname = FilenameUtils.getExtension(filename);
		String allowImgFormat = "gif,jpg,jpeg,png";
		if (!allowImgFormat.contains(extname.toLowerCase())) {
			return "NOT_IMAGE";
		}
		
		filename = Math.abs(file.getOriginalFilename().hashCode()) + RandomUtils.createRandomString( 4 ) + "." + extname;
		InputStream input = null;
		FileOutputStream fos = null;
		try {
			input = file.getInputStream();
			fos = new FileOutputStream(realpath + "/" + filename);
			IOUtils.copy(input, fos);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			IOUtils.closeQuietly(input);
			IOUtils.closeQuietly(fos);
		}
		return retpath + "/" + filename;
	}
}
