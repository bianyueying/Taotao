package bml.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import bml.common.pojo.JsonUtils;
import bml.service.ImageService;


/**
* @User 月影
* @Date 2020年1月27日
*/
@Controller
public class ImageController {
	
	@Autowired
	ImageService imageService;
	
	@RequestMapping("/pic/upload")
	@ResponseBody
	public String imageUpload(MultipartFile uploadFile){
		@SuppressWarnings("rawtypes")
		Map map = imageService.uploadImage(uploadFile);
		String json = JsonUtils.objectToJson(map);
		return json;
	}
}




