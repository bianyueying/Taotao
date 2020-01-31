package bml.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @User 月影
* @Date 2020年1月26日
*/

@Controller
public class PagesController {
	
	//跳转到首页
	@RequestMapping("/")
	public String toIndex() {
		return "index";
	}
	
	//展示其他页面
	@RequestMapping("/{page}")
	public String shoepage(@PathVariable String page) {
		return page;
	}

	
}
