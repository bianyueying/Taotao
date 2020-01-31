package bml.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bml.common.pojo.EUTreeNode;
import bml.service.ItemCatService;

/**
* @User 月影
* @Date 2020年1月26日
*/
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {
	
	@Autowired
	ItemCatService itemCatService;
	
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getCatList(@RequestParam(value = "id",defaultValue = "0") Long parentId){
		List<EUTreeNode> list = itemCatService.getTbItemByParentId(parentId);
		return list;
	}

}










