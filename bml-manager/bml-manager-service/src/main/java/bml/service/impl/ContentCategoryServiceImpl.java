package bml.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bml.common.pojo.EUTreeNode;
import bml.common.pojo.TaotaoResult;
import bml.mapper.TbContentCategoryMapper;
import bml.pojo.TbContentCategory;
import bml.pojo.TbContentCategoryExample;
import bml.pojo.TbContentCategoryExample.Criteria;
import bml.service.ContentCategoryService;

/**
* @User 月影
* @Date 2020年1月29日
*/
@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {
	
	@Autowired
	TbContentCategoryMapper contentCategoryMapper;

	/**
	 * 查询内容分类管理
	 */
	@Override
	public List<EUTreeNode> getCategoryList(Long parentId) {
		//根据ParentId查询节点列表
		TbContentCategoryExample example = new TbContentCategoryExample();
		Criteria criteria = example.createCriteria();
		criteria.andParentIdEqualTo(parentId);
		//执行查询
		List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
		List<EUTreeNode> resultList = new ArrayList<EUTreeNode>();
		for (TbContentCategory tbContentCategory : list) {
			EUTreeNode node = new EUTreeNode();
			node.setId(tbContentCategory.getId());
			node.setText(tbContentCategory.getName());
			node.setState(tbContentCategory.getIsParent()?"closed":"open");
			resultList.add(node);
		}
		return resultList;
	}

	/**
	 * 新增内容分类
	 */
	@Override
	public TaotaoResult insertNewContentCategory(long parentId, String name) {
		
		//补全对象并新增
		TbContentCategory contentCategory = new TbContentCategory();
		contentCategory.setName(name);
		contentCategory.setIsParent(false);
		contentCategory.setStatus(1);
		contentCategory.setParentId(parentId);
		contentCategory.setCreated(new Date());
		contentCategory.setUpdated(new Date());
		contentCategoryMapper.insert(contentCategory);
		
		//处理父节点的状态
		TbContentCategory parentCategory = contentCategoryMapper.selectByPrimaryKey(parentId);
		if (!parentCategory.getIsParent()) {
			parentCategory.setIsParent(true);
			//更新父节点
			contentCategoryMapper.updateByPrimaryKey(parentCategory);
		}
		return TaotaoResult.ok(contentCategory);
	}

	/**
	 * 重命名节点
	 */
	@Override
	public TaotaoResult updateContentCategory(long id, String name) {
		TbContentCategory category = contentCategoryMapper.selectByPrimaryKey(id);
		category.setName(name);
		contentCategoryMapper.updateByPrimaryKey(category);
		return TaotaoResult.ok();
	}

	/*
	 * 删除节点
	 */
	@Override
	public TaotaoResult deleteContentCategory(long id) {
		//获取要删除的Category
		TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
		//判断是否为父节点
		if (contentCategory.getIsParent()) {
			//获取所有该节点下的孩子节点
            List<EUTreeNode> list = getCategoryList(id);
            // 递归删除
            for (EUTreeNode tbcontentCategory : list) {
                deleteContentCategory(tbcontentCategory.getId());
            }
        }
		//判断父节点是否还有其他子节点
		if (getCategoryList(contentCategory.getParentId()).size() == 1) {
            TbContentCategory parentCategory = contentCategoryMapper
                    .selectByPrimaryKey(contentCategory.getParentId());
            parentCategory.setIsParent(false);
            contentCategoryMapper.updateByPrimaryKey(parentCategory);
        }
		//删除本节点
        contentCategoryMapper.deleteByPrimaryKey(id);
        return TaotaoResult.ok();
	}
}










