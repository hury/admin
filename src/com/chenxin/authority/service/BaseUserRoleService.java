// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseUserRoleService.java

package com.chenxin.authority.service;

import com.chenxin.authority.pojo.BaseUserRole;
import com.chenxin.authority.pojo.Criteria;
import java.util.List;

public abstract interface BaseUserRoleService
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract BaseUserRole selectByPrimaryKey(String paramString);

  public abstract List<BaseUserRole> selectByExample(Criteria paramCriteria);
}
