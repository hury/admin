// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseFieldsService.java

package com.chenxin.authority.service;

import com.chenxin.authority.pojo.BaseFields;
import com.chenxin.authority.pojo.Criteria;
import java.util.HashMap;
import java.util.List;

public abstract interface BaseFieldsService
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract BaseFields selectByPrimaryKey(String paramString);

  public abstract List<BaseFields> selectByExample(Criteria paramCriteria);

  public abstract HashMap<String, String> selectAllByExample(Criteria paramCriteria);

  public abstract String saveFields(Criteria paramCriteria);

  public abstract String delete(Criteria paramCriteria);
}
