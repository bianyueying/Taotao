package bml.rest.service.Impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bml.common.pojo.JsonUtils;
import bml.mapper.TbItemCatMapper;
import bml.pojo.TbItemCat;
import bml.pojo.TbItemCatExample;
import bml.pojo.TbItemCatExample.Criteria;
import bml.rest.pojo.CatNode;
import bml.rest.pojo.CatResult;
import bml.rest.service.ItemCatService;

/**
* @User 月影
* @Date 2020年1月29日
*/
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	TbItemCatMapper itemCatMapper;

	/**
	 * 商品列表查询	
	 */
	@Override
	public String getItemCatByParentId(Long parentId, String callback) {
		List<?> list = createItemCatsByParentId(parentId);
		CatResult catResult = new CatResult();
		catResult.setData(list);
		String json = JsonUtils.objectToJson(catResult);
		String result = callback+"("+json+");";
		return result;
	}
	
	@SuppressWarnings("unchecked")
	public List<?> createItemCatsByParentId(Long parentId){
		int i = 0;
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		//Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		@SuppressWarnings("rawtypes")
		List resultList = new ArrayList<>();
		for(TbItemCat itemCat : list){
			CatNode node = new CatNode();
			if(parentId == 0){
				i++;
				if(i==15){
					break;
				}
				node.setUrl("/products/"+itemCat.getId()+".html");
				node.setName("<a href='/products/"+itemCat.getId()+".html'>"+itemCat.getName()+"</a>");
				node.setItem(createItemCatsByParentId(itemCat.getId()));
				resultList.add(node);
			}else{
				if(itemCat.getIsParent()){//是父节点但不是定级节点
					node.setUrl("/products/"+itemCat.getId()+".html");
					node.setName(itemCat.getName());
					node.setItem(createItemCatsByParentId(itemCat.getId()));
					resultList.add(node);
				}else{
					resultList.add("/products/"+itemCat.getId()+".html|"+itemCat.getName());
				}
			}
		}
		return resultList;
	}
}














