package bml.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import bml.common.pojo.FtpUtil;
import bml.common.pojo.IDUtils;
import bml.service.ImageService;

/**
* @User 月影
* @Date 2020年1月27日
*/
@Service
public class ImageServiceImpl implements ImageService{

	@Value("${FTP_IMAGE_HOST}")
	private String FTP_IMAGE_HOST;
	
	@Value("${FTP_IMAGE_PORT}")
	private Integer FTP_IMAGE_PORT;
	
	@Value("${FTP_IMAGE_NAME}")
	private String FTP_IMAGE_NAME;
	
	@Value("${FTP_IMAGE_PWD}")
	private String FTP_IMAGE_PWD;
	
	@Value("${FTP_BASE_PATH}")
	private String FTP_BASE_PATH;
	
	@Value("${IMAGE_BASE_PATH}")
	private String IMAGE_BASE_PATH;
	
	@Override
	public Map<String, Object> uploadImage(MultipartFile uploadFile) { 
		Map<String, Object> map = new HashMap<>();
		try {
			String oldName = uploadFile.getOriginalFilename();
			String newName = IDUtils.genImageName()+oldName.substring(oldName.lastIndexOf("."));			
			String imagePath = new DateTime().toString("/yyyy/MM/dd");		
			FtpUtil.uploadFile(FTP_IMAGE_HOST, FTP_IMAGE_PORT, FTP_IMAGE_NAME, FTP_IMAGE_PWD, FTP_BASE_PATH, imagePath, newName, uploadFile.getInputStream());	
			map.put("error", 0);
			map.put("url", IMAGE_BASE_PATH + imagePath + "/" + newName);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("error", 1);
			map.put("message", "图片上传失败");
		}
		return map;
	} 
}











