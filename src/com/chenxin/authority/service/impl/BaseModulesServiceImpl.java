// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseModulesServiceImpl.java

package com.chenxin.authority.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chenxin.authority.dao.BaseModulesMapper;
import com.chenxin.authority.dao.BaseRoleModuleMapper;
import com.chenxin.authority.pojo.BaseModules;
import com.chenxin.authority.pojo.BaseRoleModule;
import com.chenxin.authority.pojo.BaseUsers;
import com.chenxin.authority.pojo.Criteria;
import com.chenxin.authority.pojo.Tree;
import com.chenxin.authority.pojo.TreeMenu;
import com.chenxin.authority.service.BaseModulesService;

@Service
public class BaseModulesServiceImpl
	implements BaseModulesService
{
	@Autowired
	private BaseModulesMapper baseModulesMapper;
	@Autowired
	private BaseRoleModuleMapper baseRoleModuleMapper;
	@Value("${resoved:false}")
	private boolean resoved;
	@Value("${isDisplay:false}")
	private boolean isDisplay;
	private static final Logger logger = LoggerFactory.getLogger(BaseModulesServiceImpl.class);

	public BaseModulesServiceImpl()
	{
	}

	public Tree selectAllModules(Criteria example)
	{
		example.setDistinct(true);
		example.setOrderByClause(" DISPLAY_INDEX ASC");
		if (!isDisplay)
			example.put("isDisplay", Integer.valueOf(1));
		List list = baseModulesMapper.selectByExample(example);
		TreeMenu menu = new TreeMenu(list);
		return menu.getTreeJson();
	}

	public Tree selectModulesByUser(BaseUsers baseUser)
	{
		Criteria example = new Criteria();
		example.setDistinct(true);
		if (!isDisplay)
			example.put("isDisplay", Integer.valueOf(1));
		List list = null;
		if (!resoved)
		{
			example.setOrderByClause(" DISPLAY_INDEX ASC");
			list = baseModulesMapper.selectByExample(example);
		} else
		{
			example.setOrderByClause(" a.display_index ASC");
			example.put("userId", baseUser.getUserId());
			System.out.println(baseUser.getUserId());
			list = baseModulesMapper.selectMyModules(example);
		}
		TreeMenu menu = new TreeMenu(list);
		return menu.getTreeJson();
	}
	 @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String saveModule(Criteria criteria)
	{
		String roleId = criteria.getAsString("roleId");
		ArrayList modulesIds = (ArrayList)criteria.get("modulesIdList");
		baseRoleModuleMapper.deleteByExample(criteria);
		Iterator i$ = modulesIds.iterator();
		do
		{
			if (!i$.hasNext())
				break;
			Integer moduleId = (Integer)i$.next();
			if (moduleId != null)
			{
				BaseRoleModule roleModule = new BaseRoleModule();
				roleModule.setModuleId(moduleId);
				roleModule.setRoleId(roleId);
				baseRoleModuleMapper.insert(roleModule);
			}
		} while (true);
		return "01";
	}
	 @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String saveModules(Criteria example)
	{
		BaseModules modules = (BaseModules)example.get("modules");
		int result = 0;
		if (modules.getModuleId() == null)
			result = baseModulesMapper.insertSelective(modules);
		else
			result = baseModulesMapper.updateByPrimaryKeySelective(modules);
		return result <= 0 ? "00" : "01";
	}
	 @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String delete(Criteria example)
	{
		Integer moduleId = example.getAsInteger("moduleId");
		int result = 0;
		example.clear();
		example.put("parentId", moduleId);
		baseModulesMapper.deleteByExample(example);
		result = baseModulesMapper.deleteByPrimaryKey(moduleId);
		return result <= 0 ? "00" : "01";
	}

	public int countByExample(Criteria example)
	{
		int count = baseModulesMapper.countByExample(example);
		logger.debug("count: {}", Integer.valueOf(count));
		return count;
	}

	public BaseModules selectByPrimaryKey(Integer moduleId)
	{
		return baseModulesMapper.selectByPrimaryKey(moduleId);
	}

	public List<BaseModules> selectByExample(Criteria example)
	{
		return baseModulesMapper.selectByExample(example);
	}

	public List<HashMap<String, Object>> selectByDynamicSql(Criteria example)
	{
		return baseModulesMapper.selectByDynamicSql(example);
	}

}
