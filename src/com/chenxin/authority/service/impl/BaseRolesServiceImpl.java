// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseRolesServiceImpl.java

package com.chenxin.authority.service.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.chenxin.authority.dao.BaseRoleModuleMapper;
import com.chenxin.authority.dao.BaseRolesMapper;
import com.chenxin.authority.dao.BaseUserRoleMapper;
import com.chenxin.authority.pojo.BaseRoles;
import com.chenxin.authority.pojo.Criteria;
import com.chenxin.authority.service.BaseRolesService;
@Service
public class BaseRolesServiceImpl
	implements BaseRolesService
{
	@Autowired
	private BaseRolesMapper baseRolesMapper;
	@Autowired
	private BaseUserRoleMapper baseUserRoleMapper;
	@Autowired
	private BaseRoleModuleMapper baseRoleModuleMapper;
	private static final Logger logger = LoggerFactory.getLogger(BaseRolesServiceImpl.class);

	public BaseRolesServiceImpl()
	{
	}

	public int countByExample(Criteria example)
	{
		int count = baseRolesMapper.countByExample(example);
		logger.debug("count: {}", Integer.valueOf(count));
		return count;
	}

	public BaseRoles selectByPrimaryKey(String roleId)
	{
		return baseRolesMapper.selectByPrimaryKey(roleId);
	}

	public List<BaseRoles> selectByExample(Criteria example)
	{
		return baseRolesMapper.selectByExample(example);
	}
	@Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String saveRole(BaseRoles role)
	{
		int result = 0;
		if (StringUtils.isBlank(role.getRoleId()))
			result = baseRolesMapper.insertSelective(role);
		else
			result = baseRolesMapper.updateByPrimaryKeySelective(role);
		return result <= 0 ? "00" : "01";
	}
	 @Transactional(readOnly=false, propagation=Propagation.REQUIRED, rollbackFor={Exception.class})
	public String deleteByPrimaryKey(Criteria criteria)
	{
		String roleId = criteria.getAsString("roleId");
		int result = 0;
		int count = baseUserRoleMapper.countByExample(criteria);
		if (count > 0)
		{
			return "其他用户拥有该角色，还不能删除";
		} else
		{
			baseRoleModuleMapper.deleteByExample(criteria);
			result = baseRolesMapper.deleteByPrimaryKey(roleId);
			return result <= 0 ? "00" : "01";
		}
	}

}
