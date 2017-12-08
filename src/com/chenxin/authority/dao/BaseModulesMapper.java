// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseModulesMapper.java

package com.chenxin.authority.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.chenxin.authority.pojo.BaseModules;
import com.chenxin.authority.pojo.Criteria;

public abstract interface BaseModulesMapper {

	  public abstract List<HashMap<String, Object>> selectByDynamicSql(Criteria paramCriteria);

	  public abstract int countByExample(Criteria paramCriteria);

	  public abstract int deleteByExample(Criteria paramCriteria);

	  public abstract int deleteByPrimaryKey(Integer paramInteger);

	  public abstract int insert(BaseModules paramBaseModules);

	  public abstract int insertSelective(BaseModules paramBaseModules);

	  public abstract List<BaseModules> selectByExample(Criteria paramCriteria);

	  public abstract List<BaseModules> selectMyModules(Criteria paramCriteria);

	  public abstract BaseModules selectByPrimaryKey(Integer paramInteger);

	  public abstract int updateByExampleSelective(@Param("record") BaseModules paramBaseModules, @Param("condition") Map<String, Object> paramMap);

	  public abstract int updateByExample(@Param("record") BaseModules paramBaseModules, @Param("condition") Map<String, Object> paramMap);

	  public abstract int updateByPrimaryKeySelective(BaseModules paramBaseModules);

	  public abstract int updateByPrimaryKey(BaseModules paramBaseModules);
}
