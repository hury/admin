// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseRoleModuleService.java

package com.chenxin.authority.service;

import com.chenxin.authority.pojo.BaseRoleModule;
import com.chenxin.authority.pojo.Criteria;
import java.util.List;

public abstract interface BaseRoleModuleService
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract BaseRoleModule selectByPrimaryKey(String paramString);

  public abstract List<BaseRoleModule> selectByExample(Criteria paramCriteria);
}
