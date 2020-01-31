package bml.service;

import java.util.List;

import bml.common.pojo.EUTreeNode;

/**
* @User 月影
* @Date 2020年1月26日
*/

public interface ItemCatService {
	
	List<EUTreeNode> getTbItemByParentId(long parentId);

}
