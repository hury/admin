// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseUsersService.java

package com.chenxin.authority.service;

import com.chenxin.authority.pojo.BaseUsers;
import com.chenxin.authority.pojo.Criteria;
import java.util.List;

public abstract interface BaseUsersService
{
  public abstract int countByExample(Criteria paramCriteria);

  public abstract BaseUsers selectByPrimaryKey(String paramString);

  public abstract List<BaseUsers> selectByExample(Criteria paramCriteria);

  public abstract String updatePassword(Criteria paramCriteria);

  public abstract String findPassword(Criteria paramCriteria)
    throws Exception;

  public abstract String resetPwdByPrimaryKey(Criteria paramCriteria);

  public abstract String deleteByPrimaryKey(Criteria paramCriteria);

  public abstract String selectByBaseUser(Criteria paramCriteria);

  public abstract String validateAccount(Criteria paramCriteria);

  public abstract String updateUserPassword(Criteria paramCriteria);

  public abstract String saveUser(Criteria paramCriteria);

  public abstract String updateByPrimaryKeySelective(BaseUsers paramBaseUsers);
}
