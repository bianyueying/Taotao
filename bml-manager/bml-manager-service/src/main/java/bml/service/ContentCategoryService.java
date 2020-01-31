package bml.service;

import java.util.List;

import bml.common.pojo.EUTreeNode;
import bml.common.pojo.TaotaoResult;



/**
* @User 月影
* @Date 2020年1月29日
*/

public interface ContentCategoryService {
	
	/**
	 * 内容分类管理
	 */
	List<EUTreeNode> getCategoryList(Long parentId);
	
	/**
	 * 新增内容分类
	 */
	TaotaoResult insertNewContentCategory(long parentId, String name);
	
	/**
	 * 重命名节点
	 */
	TaotaoResult updateContentCategory(long id, String name);
	
	/*
	 * 删除节点
	 */
	TaotaoResult deleteContentCategory(long id);
	
	

}
