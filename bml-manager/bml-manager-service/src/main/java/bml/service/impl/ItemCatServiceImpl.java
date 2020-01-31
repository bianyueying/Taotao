package bml.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bml.common.pojo.EUTreeNode;
import bml.mapper.TbItemCatMapper;
import bml.pojo.TbItemCat;
import bml.pojo.TbItemCatExample;
import bml.pojo.TbItemCatExample.Criteria;
import bml.service.ItemCatService;

/**
* @User 月影
* @Date 2020年1月26日
*/
@Service
public class ItemCatServiceImpl implements ItemCatService {
	
	@Autowired
	TbItemCatMapper itemCatMapper;

	@Override
	public List<EUTreeNode> getTbItemByParentId(long parentId) {
		TbItemCatExample example = new TbItemCatExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		List<TbItemCat> list = itemCatMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<EUTreeNode>();
		for(TbItemCat tbItemCat : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(tbItemCat.getId());
			node.setText(tbItemCat.getName());
			node.setState(tbItemCat.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}
}















