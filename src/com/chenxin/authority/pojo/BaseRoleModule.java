// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseRoleModule.java

package com.chenxin.authority.pojo;

import java.io.Serializable;

public class BaseRoleModule
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String roleModuleId;
	private String roleId;
	private Integer moduleId;

	public BaseRoleModule()
	{
	}

	public String getRoleModuleId()
	{
		return roleModuleId;
	}

	public void setRoleModuleId(String roleModuleId)
	{
		this.roleModuleId = roleModuleId;
	}

	public String getRoleId()
	{
		return roleId;
	}

	public void setRoleId(String roleId)
	{
		this.roleId = roleId;
	}

	public Integer getModuleId()
	{
		return moduleId;
	}

	public void setModuleId(Integer moduleId)
	{
		this.moduleId = moduleId;
	}
}
