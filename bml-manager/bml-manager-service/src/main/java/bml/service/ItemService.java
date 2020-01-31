package bml.service;

import org.springframework.web.bind.annotation.RequestParam;

import bml.common.pojo.EUDataGridResult;
import bml.common.pojo.TaotaoResult;
import bml.pojo.TbItem;

public interface ItemService {
	
	/**
	 * 查询单个商品测试
	 */
	TbItem geItemById(long itemId);
	
	/**
	 * 新增商品
	 */
	TaotaoResult addNewItem(TbItem item, String desc,String itemParam);	
	
	/**
	 * 查询所有商品
	 */
	EUDataGridResult getAllItemByPage(int page,int rows);
	
	/**
	 * 商品上架
	 */
	TaotaoResult reshelfItem(@RequestParam("ids") long [] itemId,TbItem item);
	
	/**
	 * 商品下架
	 */
	TaotaoResult dropoffItem(@RequestParam("ids") long [] itemId,TbItem item);
	
	/**
	 * 删除商品
	 */
	TaotaoResult deleteItemByIds(@RequestParam("ids") long [] itemId);
	
	/**
	 * 根据ID查询出商品描述
	 */
	TaotaoResult getItemSesc(Long id);
	
	/**
	 * 更新编辑商品
	 */
	TaotaoResult updateItem(TbItem item,String desc);
	
	
	
	
}









