// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseUsers.java

package com.chenxin.authority.pojo;

import com.chenxin.authority.common.jackjson.CustomDateTimeSerializer;
import java.io.Serializable;
import java.util.Date;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.map.annotate.JsonSerialize;
public class BaseUsers
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	private String userId;
	private String account;
	private String password;
	private String realName;
	private Short sex;
	private String email;
	private String mobile;
	private String officePhone;
	private Short errorCount;
	private Date lastLoginTime;
	private String lastLoginIp;
	private String remark;

	public BaseUsers()
	{
	}

	public String getUserId()
	{
		return userId;
	}

	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	public String getAccount()
	{
		return account;
	}

	public void setAccount(String account)
	{
		this.account = account;
	}

    @JsonIgnore
	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getRealName()
	{
		return realName;
	}

	public void setRealName(String realName)
	{
		this.realName = realName;
	}

	public Short getSex()
	{
		return sex;
	}

	public void setSex(Short sex)
	{
		this.sex = sex;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getMobile()
	{
		return mobile;
	}

	public void setMobile(String mobile)
	{
		this.mobile = mobile;
	}

	public String getOfficePhone()
	{
		return officePhone;
	}

	public void setOfficePhone(String officePhone)
	{
		this.officePhone = officePhone;
	}

    @JsonIgnore
	public Short getErrorCount()
	{
		return errorCount;
	}

	public void setErrorCount(Short errorCount)
	{
		this.errorCount = errorCount;
	}

    @JsonSerialize(using=CustomDateTimeSerializer.class)
	public Date getLastLoginTime()
	{
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime)
	{
		this.lastLoginTime = lastLoginTime;
	}

	public String getLastLoginIp()
	{
		return lastLoginIp;
	}

	public void setLastLoginIp(String lastLoginIp)
	{
		this.lastLoginIp = lastLoginIp;
	}

	public String getRemark()
	{
		return remark;
	}

	public void setRemark(String remark)
	{
		this.remark = remark;
	}
}
