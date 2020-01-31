package bml.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import bml.common.pojo.EUDataGridResult;
import bml.common.pojo.TaotaoResult;
import bml.mapper.TbItemParamMapper;
import bml.pojo.TbItemParam;
import bml.pojo.TbItemParamExample;
import bml.pojo.TbItemParamExample.Criteria;
import bml.service.ItemParamService;

/**
* @User 月影
* @Date 2020年1月27日
*/
@Service
public class ItemParamServiceImpl implements ItemParamService {
	
	@Autowired
	TbItemParamMapper itemParamMapper;
	
	/**
	 * 查询所有规格参数
	 */
	@Override
	public EUDataGridResult getItemParamList(int page, int rows) {
		TbItemParamExample example = new TbItemParamExample();
		PageHelper.startPage(page,rows);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	//商品规格参数
	@Override
	public TaotaoResult getItemParamByCid(Long cid) {
		TbItemParamExample example = new TbItemParamExample();
		Criteria criteria = example.createCriteria();
		criteria.andItemCatIdEqualTo(cid);
		List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
		//判断是否查询到结果
		if(list != null && list.size()>0) {
			return TaotaoResult.ok(list.get(0));
		}
		return TaotaoResult.ok();
	}
	
	/**
	 * 插入规格参数
	 */
	@Override
	public TaotaoResult insertItemParam(TbItemParam tbItemParam) {
		//补全新增对象属性
		tbItemParam.setCreated(new Date());
		tbItemParam.setUpdated(new Date());
		//添加到表中
		itemParamMapper.insert(tbItemParam);
		return TaotaoResult.ok();
	}

	/**
	 * 删除规格参数数组
	 */
	@Override
	public TaotaoResult deleteItemParams(long[] ids) {
		for (long id : ids) {
			itemParamMapper.deleteByPrimaryKey(id);
		}
		return TaotaoResult.ok();
	}
	
}









