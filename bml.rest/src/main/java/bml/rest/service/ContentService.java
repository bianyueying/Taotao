package bml.rest.service;

import java.util.List;
import java.util.Map;


/**
* @User 月影
* @Date 2020年1月30日
*/

public interface ContentService {
	
	/**
	 * 查询内容列表
	 */
	@SuppressWarnings("rawtypes")
	List<Map> getContentList(long contentCid);

}
