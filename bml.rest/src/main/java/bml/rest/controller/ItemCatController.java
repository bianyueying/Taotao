package bml.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bml.rest.service.ItemCatService;

/**
* @User 月影
* @Date 2020年1月29日
*/
@Controller
public class ItemCatController {
	
	@Autowired
	ItemCatService itemCatService;
	
	@RequestMapping(value="/itemcat/all",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public String getItemCatList(@RequestParam(value="parentId",defaultValue="0") Long parentId,String callback) {
		String result = itemCatService.getItemCatByParentId(parentId, callback);
		return result;
	}

}
