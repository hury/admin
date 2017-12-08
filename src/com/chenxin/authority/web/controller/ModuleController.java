// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ModuleController.java

package com.chenxin.authority.web.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenxin.authority.common.jackjson.JackJson;
import com.chenxin.authority.common.springmvc.DateConvertEditor;
import com.chenxin.authority.pojo.BaseModules;
import com.chenxin.authority.pojo.Criteria;
import com.chenxin.authority.pojo.ExceptionReturn;
import com.chenxin.authority.pojo.ExtGridReturn;
import com.chenxin.authority.pojo.ExtPager;
import com.chenxin.authority.pojo.ExtReturn;
import com.chenxin.authority.pojo.Tree;
import com.chenxin.authority.service.BaseModulesService;
import com.chenxin.authority.service.BaseRoleModuleService;
@Controller
@RequestMapping({"/module"})
public class ModuleController
{

	private static final Logger logger = LoggerFactory.getLogger(ModuleController.class);
	@Autowired
	private BaseModulesService baseModulesService;
	@Autowired
	private BaseRoleModuleService baseRoleModuleService;

	public ModuleController()
	{
	}
	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	@RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String module(Model model)
	{
		Criteria criteria = new Criteria();
		StringBuilder sb = new StringBuilder();
		sb.append("select a.module_id   as i, ").append("       a.module_name as n ").append("from   base_modules a ").append("where  a.leaf = 0 ").append("order by a.module_id asc ");
		criteria.put("dynamicSql", sb.toString());
		List list = baseModulesService.selectByDynamicSql(criteria);
		HashMap map = new HashMap();
		for (int i = 0; i < list.size(); i++)
		{
			HashMap temp = (HashMap)list.get(i);
			map.put(temp.get("I"), temp.get("N"));
		}

		map.put("0", "主菜单");
		model.addAttribute("moduleMap", JackJson.fromObjectToJson(map));
		return "user/module";
	}
	@RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST})
	public void all(PrintWriter writer)
		//throws IOException
	{
		try {
			Criteria criteria = new Criteria();
			Tree tree = baseModulesService.selectAllModules(criteria);
			String json = JackJson.fromObjectToJson(tree.getChildren());
			writer.write(json.replaceAll("\"leaf", "\"checked\":false,\"leaf"));
			writer.flush();
			writer.close();
		} catch (Exception e) {
			logger.error("Exception: ", e);
		}	
	}
	@RequestMapping({"/all"})
	@ResponseBody
	public Object all(ExtPager pager, @RequestParam(required=false) String moduleName){
		try {
			Criteria criteria = new Criteria();

			if ((pager.getLimit() != null) && (pager.getStart() != null)) {
				criteria.setOracleEnd(Integer.valueOf(pager.getLimit()
						.intValue()
						+ pager.getStart().intValue()));
				criteria.setOracleStart(pager.getStart());
			}

			if ((StringUtils.isNotBlank(pager.getDir()))
					&& (StringUtils.isNotBlank(pager.getSort())))
				criteria.setOrderByClause(new StringBuilder().append(
						pager.getSort()).append(" ").append(pager.getDir())
						.toString());
			else {
				criteria.setOrderByClause(" PARENT_ID asc,DISPLAY_INDEX asc ");
			}
			if (StringUtils.isNotBlank(moduleName)) {
				criteria.put("moduleNameLike", moduleName);
			}
			List list = this.baseModulesService.selectByExample(criteria);
			int total = this.baseModulesService.countByExample(criteria);
			logger.debug("total:{}", Integer.valueOf(total));
			return new ExtGridReturn(total, list);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping( { "/{roleId}" })
	@ResponseBody
	public Object selectModulesByRoleId(@PathVariable String roleId) {
		try {
			if (StringUtils.isBlank(roleId)) {
				return new ExtReturn(false, "角色ID不能为空！");
			}
			Criteria criteria = new Criteria();
			criteria.put("roleId", roleId);
			List list = this.baseRoleModuleService.selectByExample(criteria);
			return list;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping( { "/saverole" })
	@ResponseBody
	public Object save(@RequestParam String roleId, @RequestParam String moduleIds) {
		try {
			ArrayList modulesIdList = new ArrayList();
			if (StringUtils.isBlank(roleId)) {
				return new ExtReturn(false, "角色不能为空！");
			}
			if (StringUtils.isBlank(moduleIds)) {
				return new ExtReturn(false, "选择的资源不能为空！");
			}
			String[] modules = StringUtils.split(moduleIds, ",");
			if ((null == modules) || (modules.length == 0)) {
				return new ExtReturn(false, "选择的资源不能为空！");
			}
			for (int i = 0; i < modules.length; i++) {
				modulesIdList.add(new Integer(modules[i]));
			}

			logger.debug("save() - String roleId={}", roleId);
			logger.debug("save() - String moduleIds={}", moduleIds);
			Criteria criteria = new Criteria();
			criteria.put("modulesIdList", modulesIdList);
			criteria.put("roleId", roleId);
			String result = this.baseModulesService.saveModule(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, "保存成功！");
			if ("00".equals(result)) {
				return new ExtReturn(false, "保存失败！");
			}
			return new ExtReturn(false, result);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping( { "/save" })
	@ResponseBody
	public Object save(BaseModules modules) {
		try {
			if (modules == null) {
				return new ExtReturn(false, "模块不能为空！");
			}
			if (StringUtils.isBlank(modules.getModuleName())) {
				return new ExtReturn(false, "模块名称不能为空！");
			}
			Criteria criteria = new Criteria();
			criteria.put("modules", modules);
			String result = this.baseModulesService.saveModules(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, "保存成功！");
			if ("00".equals(result)) {
				return new ExtReturn(false, "保存失败！");
			}
			return new ExtReturn(false, result);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping( { "/del/{moduleId}" })
	@ResponseBody
	public Object delete(@PathVariable String moduleId) {
		try {
			if (StringUtils.isBlank(moduleId)) {
				return new ExtReturn(false, "模块主键不能为空！");
			}
			Criteria criteria = new Criteria();
			criteria.put("moduleId", moduleId);
			String result = this.baseModulesService.delete(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, "删除成功！");
			if ("00".equals(result)) {
				return new ExtReturn(false, "删除失败！");
			}
			return new ExtReturn(false, result);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

}
