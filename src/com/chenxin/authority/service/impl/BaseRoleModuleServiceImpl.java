// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseRoleModuleServiceImpl.java

package com.chenxin.authority.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenxin.authority.dao.BaseRoleModuleMapper;
import com.chenxin.authority.pojo.BaseRoleModule;
import com.chenxin.authority.pojo.Criteria;
import com.chenxin.authority.service.BaseRoleModuleService;
@Service
public class BaseRoleModuleServiceImpl
	implements BaseRoleModuleService
{
	@Autowired
	private BaseRoleModuleMapper baseRoleModuleMapper;
	private static final Logger logger = LoggerFactory.getLogger(BaseRoleModuleServiceImpl.class);

	public BaseRoleModuleServiceImpl()
	{
	}

	public int countByExample(Criteria example)
	{
		int count = baseRoleModuleMapper.countByExample(example);
		logger.debug("count: {}", Integer.valueOf(count));
		return count;
	}

	public BaseRoleModule selectByPrimaryKey(String roleModuleId)
	{
		return baseRoleModuleMapper.selectByPrimaryKey(roleModuleId);
	}

	public List<BaseRoleModule> selectByExample(Criteria example)
	{
		return baseRoleModuleMapper.selectByExample(example);
	}

}
