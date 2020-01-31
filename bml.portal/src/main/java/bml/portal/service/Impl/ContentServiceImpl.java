package bml.portal.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import bml.common.pojo.HttpClientUtil;
import bml.common.pojo.JsonUtils;
import bml.common.pojo.TaotaoResult;
import bml.portal.service.ContentService;

/**
* @User 月影
* @Date 2020年1月30日
*/
@Service
public class ContentServiceImpl implements ContentService {
	
	/**
	 * 查询内容列表
	 */
	@Override
	public String getContentList() {
		String resp = HttpClientUtil.doGet("http://localhost:8081/rest/content/89");
		TaotaoResult taotao = TaotaoResult.format(resp);
		if(taotao.getStatus()==200){
			List<?> list = (List<?>) taotao.getData();
			String result = JsonUtils.objectToJson(list);
			return result;
		}
		return null;
	}
}






















