package bml.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import bml.common.pojo.EUTreeNode;
import bml.common.pojo.TaotaoResult;
import bml.service.ContentCategoryService;

/**
* @User 月影
* @Date 2020年1月29日
*/
@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {
	
	@Autowired
	ContentCategoryService contentCategoryService;
	
	/**
	 * 内容分类管理列表
	 */
	@RequestMapping("/list")
	@ResponseBody
	public List<EUTreeNode> getConCatByParentId(@RequestParam(value="id",defaultValue="0") Long parentId){
		List<EUTreeNode> result = contentCategoryService.getCategoryList(parentId);
		return result;
	}
	
	/**
	 * 新增内容分类节点
	 */
	@RequestMapping("/create")
	@ResponseBody
	public TaotaoResult insertNewContentCategory(Long parentId, String name) {
		TaotaoResult result = contentCategoryService.insertNewContentCategory(parentId, name);
		return result;
	}
	
	/**
	 * 重命名节点
	 */
	@RequestMapping("/update")
	@ResponseBody
	public TaotaoResult updateContentCategory(Long id, String name) {
		TaotaoResult result = contentCategoryService.updateContentCategory(id, name);
		return result;
	}
	
	/**
	 * 删除节点
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public TaotaoResult deleteContentCategory(Long id) {
		TaotaoResult result = contentCategoryService.deleteContentCategory(id);
		return result;
	}

}











