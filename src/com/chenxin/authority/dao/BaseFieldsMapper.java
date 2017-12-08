// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseFieldsMapper.java

package com.chenxin.authority.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.chenxin.authority.pojo.BaseFields;
import com.chenxin.authority.pojo.Criteria;

public abstract interface BaseFieldsMapper
{

	  public abstract int countByExample(Criteria paramCriteria);

	  public abstract int deleteByExample(Criteria paramCriteria);

	  public abstract int deleteByPrimaryKey(String paramString);

	  public abstract int insert(BaseFields paramBaseFields);

	  public abstract int insertSelective(BaseFields paramBaseFields);

	  public abstract List<BaseFields> selectByExample(Criteria paramCriteria);

	  public abstract BaseFields selectByPrimaryKey(String paramString);

	  public abstract int updateByExampleSelective(@Param("record") BaseFields paramBaseFields, @Param("condition") Map<String, Object> paramMap);

	  public abstract int updateByExample(@Param("record") BaseFields paramBaseFields, @Param("condition") Map<String, Object> paramMap);

	  public abstract int updateByPrimaryKeySelective(BaseFields paramBaseFields);

	  public abstract int updateByPrimaryKey(BaseFields paramBaseFields);
}
