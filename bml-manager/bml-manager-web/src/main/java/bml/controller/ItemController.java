package bml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bml.common.pojo.EUDataGridResult;
import bml.common.pojo.TaotaoResult;
import bml.pojo.TbItem;
import bml.service.ItemService;

@Controller
@RequestMapping("/item")
public class ItemController {
	
	@Autowired
	ItemService itemService;
	
	//测试查询单个商品
	@RequestMapping(value = "/{itemId}",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public TbItem geTbItemById(@PathVariable Long itemId) {
		TbItem item = itemService.geItemById(itemId);
		return item;
	}
	
	//查询所有商品
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemList(Integer page,Integer rows) {
		EUDataGridResult result = itemService.getAllItemByPage(page, rows);
		return result;
	}
	
	//上架商品
	@RequestMapping(value = "/reshelf", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult reshelfItem(@RequestParam("ids") long[] itemId,TbItem item) {
		TaotaoResult result = itemService.reshelfItem(itemId,item);
		return result;
	}
	
	//下架商品
	@RequestMapping(value = "/instock", method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult dropoffItem(@RequestParam("ids") long[] itemId,TbItem item) {
		TaotaoResult result = itemService.dropoffItem(itemId,item);
		return result;
	}
	
	//删除商品
	@RequestMapping(value = "/delete",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult deleteTbItemById(@RequestParam("ids") long [] itemId) {
		TaotaoResult result = itemService.deleteItemByIds(itemId);
		return result;
	}
	
	//新增商品
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	@ResponseBody
	public TaotaoResult addNewItem(TbItem item, String desc, String itemParams) {
		TaotaoResult result = itemService.addNewItem(item,desc,itemParams);
		return result;
	}
	
	//查询商品描述
	@RequestMapping("/desc/{id}")
	@ResponseBody
	public TaotaoResult getItemDesc(@PathVariable("id") Long id) {
		TaotaoResult result = itemService.getItemSesc(id);
		return result;
	}
	
	//更新商品
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateItem(TbItem item,String desc) {
		TaotaoResult result = itemService.updateItem(item, desc);
		return result;
	}
	
	
}












