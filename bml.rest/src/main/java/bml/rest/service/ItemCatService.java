package bml.rest.service;


/**
* @User 月影
* @Date 2020年1月29日
*/

public interface ItemCatService {
	
	/**
	 * 商品列表查询
	 */
	String getItemCatByParentId(Long parentId, String callback);

}
