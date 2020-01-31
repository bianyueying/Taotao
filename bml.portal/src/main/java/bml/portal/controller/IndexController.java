package bml.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import bml.portal.service.ContentService;

/**
* @User 月影
* @Date 2020年1月28日
*/
@Controller
public class IndexController {
	
	@Autowired
	ContentService contentService;
	
	@RequestMapping("/index")
	public String showIndex(Model model) {
		String contentList = contentService.getContentList();
		model.addAttribute("ad1",contentList);
		return "index";
	}

}
