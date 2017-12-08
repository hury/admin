// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   RoleController.java

package com.chenxin.authority.web.controller;

import java.util.Date;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenxin.authority.common.springmvc.DateConvertEditor;
import com.chenxin.authority.pojo.BaseRoles;
import com.chenxin.authority.pojo.Criteria;
import com.chenxin.authority.pojo.ExceptionReturn;
import com.chenxin.authority.pojo.ExtGridReturn;
import com.chenxin.authority.pojo.ExtPager;
import com.chenxin.authority.pojo.ExtReturn;
import com.chenxin.authority.service.BaseRolesService;
@Controller
@RequestMapping({"/role"})
public class RoleController
{

	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	private BaseRolesService baseRolesService;

	@InitBinder
	public void initBinder(WebDataBinder binder)
	{
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}
	@RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.GET})
	public String role()
	{
		return "user/role";
	}
	@RequestMapping(method={org.springframework.web.bind.annotation.RequestMethod.POST})
	@ResponseBody
	public Object all(ExtPager pager, @RequestParam(required=false) String roleName)
	{
		Criteria criteria = new Criteria();
		if (pager.getLimit() != null && pager.getStart() != null)
		{
			criteria.setOracleEnd(Integer.valueOf(pager.getLimit().intValue() + pager.getStart().intValue()));
			criteria.setOracleStart(pager.getStart());
		}
		if (StringUtils.isNotBlank(pager.getDir()) && StringUtils.isNotBlank(pager.getSort()))
			criteria.setOrderByClause((new StringBuilder()).append(pager.getSort()).append(" ").append(pager.getDir()).toString());
		if (StringUtils.isNotBlank(roleName))
			criteria.put("roleNameLike", roleName);
		java.util.List list = baseRolesService.selectByExample(criteria);
		int total = baseRolesService.countByExample(criteria);
		logger.debug("total:{}", Integer.valueOf(total));
		return new ExtGridReturn(total, list);
	}

	@RequestMapping( { "/save" })
	@ResponseBody
	public Object save(BaseRoles role) {
		try {
			if (role == null) {
				return new ExtReturn(false, "角色不能为空!");
			}
			if (StringUtils.isBlank(role.getRoleName())) {
				return new ExtReturn(false, "角色名称不能为空!");
			}
			String result = this.baseRolesService.saveRole(role);
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

	@RequestMapping( { "/del/{roleId}" })
	@ResponseBody
	public Object delete(@PathVariable
	String roleId) {
		try {
			if (StringUtils.isBlank(roleId)) {
				return new ExtReturn(false, "角色主键不能为空！");
			}
			Criteria criteria = new Criteria();
			criteria.put("roleId", roleId);
			String result = this.baseRolesService.deleteByPrimaryKey(criteria);
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
