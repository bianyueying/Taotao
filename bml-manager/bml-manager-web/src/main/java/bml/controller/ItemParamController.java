package bml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bml.common.pojo.EUDataGridResult;
import bml.common.pojo.TaotaoResult;
import bml.pojo.TbItemParam;
import bml.service.ItemParamService;

/**
* @User 月影
* @Date 2020年1月27日
*/
@Controller
@RequestMapping("/item/param")
public class ItemParamController {
	
	@Autowired
	ItemParamService itemParamService;
	
	/**
	 * 查询所有规格参数
	 */
	@RequestMapping("/list")
	@ResponseBody
	public EUDataGridResult getItemParamList(Integer page,Integer rows) {
		EUDataGridResult result = itemParamService.getItemParamList(page, rows);
		return result;
	}
	
	@RequestMapping("/query/itemcatid/{cid}")
	@ResponseBody
	public TaotaoResult queryByCid(@PathVariable Long cid) {
		TaotaoResult result = itemParamService.getItemParamByCid(cid);
		return result;
	}
	
	/**
	 * 插入规格参数
	 */
	@RequestMapping("/save/{cid}")
	@ResponseBody
	public TaotaoResult insertItemParam(@PathVariable("cid")Long cid,String paramData) {
		//创建对象
		TbItemParam itemParam = new TbItemParam();
		itemParam.setItemCatId(cid);
		itemParam.setParamData(paramData);
		TaotaoResult result = itemParamService.insertItemParam(itemParam);
		return result;
	}
	
	/**
	 * 删除规格参数数组
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteItemParams(@RequestParam("ids") long[] ids) {
		TaotaoResult result = itemParamService.deleteItemParams(ids);
		return result;
	}
	
}















