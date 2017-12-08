// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseUsersMapper.java

package com.chenxin.authority.dao;

import com.chenxin.authority.pojo.BaseUsers;
import com.chenxin.authority.pojo.Criteria;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;

public abstract interface BaseUsersMapper
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract int deleteByExample(Criteria paramCriteria);

  public abstract int deleteByPrimaryKey(String paramString);

  public abstract int insert(BaseUsers paramBaseUsers);

  public abstract int insertSelective(BaseUsers paramBaseUsers);

  public abstract List<BaseUsers> selectByExample(Criteria paramCriteria);

  public abstract BaseUsers selectByPrimaryKey(String paramString);

  public abstract int updateByExampleSelective(@Param("record") BaseUsers paramBaseUsers, @Param("condition") Map<String, Object> paramMap);

  public abstract int updateByExample(@Param("record") BaseUsers paramBaseUsers, @Param("condition") Map<String, Object> paramMap);

  public abstract int updateByPrimaryKeySelective(BaseUsers paramBaseUsers);

  public abstract int updateByPrimaryKey(BaseUsers paramBaseUsers);
}
