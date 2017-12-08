// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseUserRole.java

package com.chenxin.authority.pojo;

import java.io.Serializable;

public class BaseUserRole
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String userRoleId;
	private String userId;
	private String roleId;

	public BaseUserRole()
	{
	}

	public String getUserRoleId()
	{
		return userRoleId;
	}

	public void setUserRoleId(String userRoleId)
	{
		this.userRoleId = userRoleId;
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getRoleId()
	{
		return roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}
}
