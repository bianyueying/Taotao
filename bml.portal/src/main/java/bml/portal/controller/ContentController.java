package bml.portal.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
* @User 月影
* @Date 2020年1月30日
*/
@Controller
public class ContentController {
	
	@RequestMapping(value="/httpclient/post.do",method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getHttpClient(String username,String password){
		String s = "abcdefg";
		System.out.println(username+"--------------------"+password);
		return s;
	}
}
