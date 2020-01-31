package bml.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bml.common.pojo.JsonUtils;
import bml.mapper.TbItemParamItemMapper;
import bml.pojo.TbItemParamItem;
import bml.pojo.TbItemParamItemExample;
import bml.pojo.TbItemParamItemExample.Criteria;
import bml.service.ItemParamItemService;


/**
* @User 月影
* @Date 2020年1月28日
*/
@Service
public class ItemParamItemServiceImpl implements ItemParamItemService {
	
	@Autowired
	TbItemParamItemMapper itemParamItemMapper;

	/**
	 * 展示商品规格参数
	 */
	@Override
	@SuppressWarnings("rawtypes")
	public String queryItemParamByItemId(Long itemId) {
		TbItemParamItemExample example = new TbItemParamItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemIdEqualTo(itemId);
		List<TbItemParamItem> list = itemParamItemMapper.selectByExampleWithBLOBs(example);
		if (list == null || list.size() == 0) {
			return "";
		}
		//取规格参数信息
		TbItemParamItem itemParamItem = list.get(0);
		String paramData = itemParamItem.getParamData();
		
		//生成HTML 把规格参数JSON数据转换成JAVA对象
		List<Map> jsonList = JsonUtils.jsonToList(paramData, Map.class);
		StringBuffer sb = new StringBuffer();
		sb.append("<table cellpadding=\"0\" cellspacing=\"1\" width=\"100%\" border=\"1\" class=\"Ptable\">\n");
		sb.append("    <tbody>\n");
		for(Map m1:jsonList) {
			sb.append("        <tr>\n");
			sb.append("            <th class=\"tdTitle\" colspan=\"2\">"+m1.get("group")+"</th>\n");
			sb.append("        </tr>\n");
			
			@SuppressWarnings("unchecked")
			List<Map> list2 = (List<Map>) m1.get("params");
			for(Map m2:list2) {
				sb.append("        <tr>\n");
				sb.append("            <td class=\"tdTitle\">"+m2.get("k")+"</td>\n");
				sb.append("            <td>"+m2.get("v")+"</td>\n");
				sb.append("        </tr>\n");
			}
		}
		sb.append("    </tbody>\n");
		sb.append("</table>");
		return sb.toString();
	}
}











