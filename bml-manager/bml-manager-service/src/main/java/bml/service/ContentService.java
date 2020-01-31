package bml.service;

import bml.common.pojo.EUDataGridResult;
import bml.common.pojo.TaotaoResult;
import bml.pojo.TbContent;

/**
* @User 月影
* @Date 2020年1月29日
*/

public interface ContentService {
	
	/**
	 * 展示内容列表
	 */
	EUDataGridResult getContentItemList(int page,int rows);
	
	/**
	 * 新增内容
	 */
	TaotaoResult insertNewContent(TbContent content);
	
	/**
	 * 删除内容
	 */
	TaotaoResult deleteContents(long [] ids);
	
	/**
	 * 更新内容
	 */
	TaotaoResult updateContent(TbContent content);

}
