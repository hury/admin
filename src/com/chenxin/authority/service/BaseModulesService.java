// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseModulesService.java

package com.chenxin.authority.service;

import com.chenxin.authority.pojo.BaseModules;
import com.chenxin.authority.pojo.BaseUsers;
import com.chenxin.authority.pojo.Criteria;
import com.chenxin.authority.pojo.Tree;
import java.util.HashMap;
import java.util.List;

public abstract interface BaseModulesService
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract BaseModules selectByPrimaryKey(Integer paramInteger);

  public abstract List<BaseModules> selectByExample(Criteria paramCriteria);

  public abstract Tree selectModulesByUser(BaseUsers paramBaseUsers);

  public abstract Tree selectAllModules(Criteria paramCriteria);

  public abstract String saveModule(Criteria paramCriteria);

  public abstract String saveModules(Criteria paramCriteria);

  public abstract String delete(Criteria paramCriteria);

  public abstract List<HashMap<String, Object>> selectByDynamicSql(Criteria paramCriteria);
}
