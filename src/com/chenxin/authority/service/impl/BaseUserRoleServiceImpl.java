// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseUserRoleServiceImpl.java

package com.chenxin.authority.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chenxin.authority.dao.BaseUserRoleMapper;
import com.chenxin.authority.pojo.BaseUserRole;
import com.chenxin.authority.pojo.Criteria;
import com.chenxin.authority.service.BaseUserRoleService;
@Service
public class BaseUserRoleServiceImpl
	implements BaseUserRoleService
{
	@Autowired
	private BaseUserRoleMapper baseUserRoleMapper;
	private static final Logger logger = LoggerFactory.getLogger(BaseUserRoleServiceImpl.class);

	public BaseUserRoleServiceImpl()
	{
	}

	public int countByExample(Criteria example)
	{
		int count = baseUserRoleMapper.countByExample(example);
		logger.debug("count: {}", Integer.valueOf(count));
		return count;
	}

	public BaseUserRole selectByPrimaryKey(String userRoleId)
	{
		return baseUserRoleMapper.selectByPrimaryKey(userRoleId);
	}

	public List<BaseUserRole> selectByExample(Criteria example)
	{
		return baseUserRoleMapper.selectByExample(example);
	}

}
