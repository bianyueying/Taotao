package bml.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bml.common.pojo.EUDataGridResult;
import bml.common.pojo.TaotaoResult;
import bml.pojo.TbContent;
import bml.service.ContentService;

/**
* @User 月影
* @Date 2020年1月29日
*/
@Controller
@RequestMapping(value="/content")
public class ContentController {
	
	@Autowired
	ContentService contentService;
	
	/**
	 * 查询内容列表
	 */
	@RequestMapping(value="/query/list",produces=MediaType.APPLICATION_JSON_VALUE+";charset=utf-8")
	@ResponseBody
	public EUDataGridResult getContentItemList(Integer page, Integer rows) {
		EUDataGridResult result = contentService.getContentItemList(page, rows);
		return result;
	}

	/**
	 * 新增内容
	 */
	@RequestMapping("/save")
	@ResponseBody
	public TaotaoResult insertNewContent(TbContent content) {
		TaotaoResult result = contentService.insertNewContent(content);
		return result;
	}
	
	/**
	 * 删除内容
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContents(long [] ids) {
		TaotaoResult result = contentService.deleteContents(ids);
		return result;
	}
	
	/**
	 * 更新编辑内容
	 */
	@RequestMapping("/edit")
	@ResponseBody
	public TaotaoResult updateContent(TbContent content) {
		TaotaoResult result = contentService.updateContent(content);
		return result;
	}

}













