package bml.service;

import org.springframework.web.bind.annotation.RequestParam;

import bml.common.pojo.EUDataGridResult;
import bml.common.pojo.TaotaoResult;
import bml.pojo.TbItemParam;

/**
* @User 月影
* @Date 2020年1月27日
*/

public interface ItemParamService {
	
	TaotaoResult getItemParamByCid(Long cid);
	
	/**
	 * 插入规格参数
	 */
	TaotaoResult insertItemParam(TbItemParam tbItemParam);
	
	/**
	 * 查询所有规格参数
	 */
	EUDataGridResult getItemParamList(int page, int rows);
	
	/**
	 * 删除规格参数数组
	 */
	TaotaoResult deleteItemParams(@RequestParam("ids") long [] ids);

}
