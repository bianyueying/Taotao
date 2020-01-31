package bml.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import bml.common.pojo.EUDataGridResult;
import bml.common.pojo.TaotaoResult;
import bml.mapper.TbContentMapper;
import bml.pojo.TbContent;
import bml.pojo.TbContentExample;
import bml.service.ContentService;

/**
* @User 月影
* @Date 2020年1月29日
*/
@Service
public class ContentServiceImpl implements ContentService {
	
	@Autowired
	TbContentMapper contentMapper;

	/**
	 * 查询内容列表
	 */
	@Override
	public EUDataGridResult getContentItemList(int page, int rows) {
		//查询商品列表
		TbContentExample example = new TbContentExample();
		//分页处理
		PageHelper.startPage(page,rows);
		List<TbContent> list = contentMapper.selectByExampleWithBLOBs(example);
		//创建一个返回值对象
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		//取记录总条数
		PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	/**
	 * 新增内容
	 */
	@Override
	public TaotaoResult insertNewContent(TbContent content) {
		try {
			content.setCreated(new Date());
			content.setUpdated(new Date());
			contentMapper.insert(content);
			return TaotaoResult.ok();
		} catch (Exception e) {
			return TaotaoResult.build(400, "新增失败");
		}
	}

	/**
	 * 删除内容
	 */
	@Override
	public TaotaoResult deleteContents(long[] ids) {
		for (long id : ids) {
			contentMapper.deleteByPrimaryKey(id);
		}
		return TaotaoResult.ok();
	}

	/**
	 * 更新内容
	 */
	@Override
	public TaotaoResult updateContent(TbContent content) {
		content.setUpdated(new Date());
		/**
		 * updateByPrimaryKeySelective会对字段进行判断再更新(如果为Null就忽略更新) 适用于更新某一字段
		 * dateByPrimaryKey对注入的字段全部更新
		 */
		contentMapper.updateByPrimaryKeySelective(content);
		return TaotaoResult.ok();
	}

}











