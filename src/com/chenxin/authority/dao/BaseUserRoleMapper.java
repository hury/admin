// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseUserRoleMapper.java

package com.chenxin.authority.dao;

import com.chenxin.authority.pojo.BaseUserRole;
import com.chenxin.authority.pojo.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public abstract interface BaseUserRoleMapper
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract int deleteByExample(Criteria paramCriteria);

  public abstract int deleteByPrimaryKey(String paramString);

  public abstract int insert(BaseUserRole paramBaseUserRole);

  public abstract int insertSelective(BaseUserRole paramBaseUserRole);

  public abstract List<BaseUserRole> selectByExample(Criteria paramCriteria);

  public abstract BaseUserRole selectByPrimaryKey(String paramString);

  public abstract int updateByExampleSelective(@Param("record") BaseUserRole paramBaseUserRole, @Param("condition") Map<String, Object> paramMap);

  public abstract int updateByExample(@Param("record") BaseUserRole paramBaseUserRole, @Param("condition") Map<String, Object> paramMap);

  public abstract int updateByPrimaryKeySelective(BaseUserRole paramBaseUserRole);

  public abstract int updateByPrimaryKey(BaseUserRole paramBaseUserRole);
}
