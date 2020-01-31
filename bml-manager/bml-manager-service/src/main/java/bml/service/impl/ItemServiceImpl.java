package bml.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import bml.common.pojo.EUDataGridResult;
import bml.common.pojo.IDUtils;
import bml.common.pojo.TaotaoResult;
import bml.mapper.TbItemDescMapper;
import bml.mapper.TbItemMapper;
import bml.mapper.TbItemParamItemMapper;
import bml.pojo.TbItem;
import bml.pojo.TbItemDesc;
import bml.pojo.TbItemDescExample;
import bml.pojo.TbItemExample;
import bml.pojo.TbItemExample.Criteria;
import bml.pojo.TbItemParamItem;
import bml.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	TbItemMapper itemMapper;
	
	@Autowired
	TbItemDescMapper itemDescMapper;
	
	@Autowired
	TbItemParamItemMapper itemParamItemMapper;
	
	/**
	 * 查询单个商品测试
	 */
	@Override
	public TbItem geItemById(long itemId) {
		TbItem item = itemMapper.selectByPrimaryKey(itemId);
		return item;
	}

	/**
	 * 查询所有商品
	 */
	@Override
	public EUDataGridResult getAllItemByPage(int page, int rows) {
		TbItemExample example = new TbItemExample();
		PageHelper.startPage(page,rows);
		List<TbItem> list = itemMapper.selectByExample(example);
		EUDataGridResult result = new EUDataGridResult();
		result.setRows(list);
		PageInfo<TbItem> pageInfo = new PageInfo<TbItem>(list);
		result.setTotal(pageInfo.getTotal());
		return result;
	}

	/**
	 * 商品上架
	 */
	@Override
	public TaotaoResult reshelfItem(long[] itemId, TbItem item) {
		for (long id : itemId) {
			item = itemMapper.selectByPrimaryKey(id);
			item.setStatus((byte) 1);
			//创建时间不变
			item.setCreated(item.getCreated());
			//更改更新时间
			item.setUpdated(new Date());
			itemMapper.updateByPrimaryKeySelective(item);
		}
		return TaotaoResult.ok();
	}
	
	/**
	 * 商品下架
	 */
	@Override
	public TaotaoResult dropoffItem(long[] itemId, TbItem item) {
		for (long id : itemId) {
			item = itemMapper.selectByPrimaryKey(id);
			item.setStatus((byte) 2);
			//创建时间不变
			item.setCreated(item.getCreated());
			//更改更新时间
			item.setUpdated(new Date());
			itemMapper.updateByPrimaryKeySelective(item);
		}
		return TaotaoResult.ok();
	}
	
	/**
	 * 删除商品
	 */
	@Override
	public TaotaoResult deleteItemByIds(long[] itemId) {
		for (long id : itemId) {
			itemMapper.deleteByPrimaryKey(id);
		}
		return TaotaoResult.ok();
	}

	/**
	 * 新增商品
	 */
	@Override
	public TaotaoResult addNewItem(TbItem item,String desc,String itemParam) {
		try {
			//商品入库
			Long itemId = IDUtils.genItemId();
			item.setId(itemId);
			item.setStatus((byte) 1);
			item.setCreated(new Date());
			item.setUpdated(new Date());
			itemMapper.insert(item);
			
			//商品描述入库
			TbItemDesc itemDesc = new TbItemDesc();
			itemDesc.setItemId(itemId);
			itemDesc.setItemDesc(desc);
			itemDesc.setUpdated(new Date());
			itemDesc.setCreated(new Date());
			itemDescMapper.insert(itemDesc);
			
			//商品规格入库
			TbItemParamItem itemParamItem = new TbItemParamItem();
			itemParamItem.setItemId(itemId);
			itemParamItem.setParamData(itemParam);
			itemParamItem.setCreated(new Date());
			itemParamItem.setUpdated(new Date());
			itemParamItemMapper.insert(itemParamItem);
			
			return TaotaoResult.ok();
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(405, "新增商品出错");
		}
	}

	/**
	 * 根据ID查询出商品描述
	 */
	@Override
	public TaotaoResult getItemSesc(Long id) {
		TbItemDesc desc = itemDescMapper.selectByPrimaryKey(id);
		return TaotaoResult.ok(desc);
	}
	
	/**
	 * 更新编辑商品
	 */
	@Override
	public TaotaoResult updateItem(TbItem item, String desc) {
		//根据商品id更新商品表
		Long id = item.getId();
		TbItemExample example = new TbItemExample();
		Criteria criteria = example.createCriteria();
		criteria.andIdEqualTo(id);
		itemMapper.updateByExampleSelective(item, example);
		
		//根据商品id更新商品描述表
		TbItemDesc itemDesc = new TbItemDesc();
		itemDesc.setItemDesc(desc);
		TbItemDescExample descExample = new TbItemDescExample();
		bml.pojo.TbItemDescExample.Criteria criteria2 = descExample.createCriteria();
		criteria2.andItemIdEqualTo(id);
		itemDescMapper.updateByExampleSelective(itemDesc, descExample);
		
		return TaotaoResult.ok();
	}

}










