package bml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import bml.service.ItemParamItemService;

/**
* @User 月影
* @Date 2020年1月28日
*/
@Controller
public class ItemParamItemController {
	
	@Autowired
	ItemParamItemService itemParamItemService;
	
	/**
	 * 展示商品规格
	 */
	@RequestMapping("/showitem/{itemId}")
	public String showItemParam(@PathVariable Long itemId,Model model) {
		String string = itemParamItemService.queryItemParamByItemId(itemId);
		model.addAttribute("itemParam",string);
		return "item";
	}
}











