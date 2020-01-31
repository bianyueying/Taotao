package bml.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import bml.common.pojo.ExceptionUtil;
import bml.common.pojo.TaotaoResult;
import bml.rest.service.ContentService;

/**
* @User 月影
* @Date 2020年1月30日
*/
@Controller
@RequestMapping("/content")
public class ContentController {
	
	@Autowired
	ContentService contentService;
	
	/**
	 * 查询内容列表
	 */
	@RequestMapping("/{contentCid}")
	@ResponseBody
	public TaotaoResult getContentByCid(@PathVariable("contentCid") Long contentCid) {
		try {
			List<?> list = contentService.getContentList(contentCid);
			return TaotaoResult.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return TaotaoResult.build(500, ExceptionUtil.getStackTrace(e));
		}
	}
}













