// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseRolesService.java

package com.chenxin.authority.service;

import com.chenxin.authority.pojo.BaseRoles;
import com.chenxin.authority.pojo.Criteria;
import java.util.List;

public abstract interface BaseRolesService
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract BaseRoles selectByPrimaryKey(String paramString);

  public abstract List<BaseRoles> selectByExample(Criteria paramCriteria);

  public abstract String saveRole(BaseRoles paramBaseRoles);

  public abstract String deleteByPrimaryKey(Criteria paramCriteria);
}
