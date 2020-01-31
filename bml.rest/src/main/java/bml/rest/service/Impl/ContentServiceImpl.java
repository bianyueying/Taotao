package bml.rest.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bml.mapper.TbContentMapper;
import bml.pojo.TbContent;
import bml.pojo.TbContentExample;
import bml.pojo.TbContentExample.Criteria;
import bml.rest.service.ContentService;

/**
* @User 月影
* @Date 2020年1月30日
*/
@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	TbContentMapper contentMapper;

	/**
	 * 查询内容列表
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public List<Map> getContentList(long contentCid) {
		//根据内容分类ID查询出内容列表
		TbContentExample example = new TbContentExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(contentCid);
		//执行查询
		List<TbContent> list = contentMapper.selectByExample(example);
		List<Map> resultList = new ArrayList<>();
		for(TbContent content : list){
			Map map = new HashMap();
			map.put("src", content.getPic());
			map.put("height", 240);
			map.put("width", 670);
			map.put("srcB", content.getPic2());
			map.put("heightB", 240);
			map.put("widthB", 550);
			map.put("alt", "");
			map.put("href", content.getUrl());
			resultList.add(map);
		}
		return resultList;
	}
}








