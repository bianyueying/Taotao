package bml.service;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

/**
* @User 月影
* @Date 2020年1月27日
*/

public interface ImageService {
	
	/**
	 * 图片上传 特么的为什么这个功能这么慢？
	 */
	@SuppressWarnings("rawtypes")
	Map uploadImage(MultipartFile uploadFile);

}
