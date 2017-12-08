// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Criteria.java

package com.chenxin.authority.pojo;

import com.chenxin.authority.common.utils.TypeCaseHelper;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class Criteria
{

	private Map<String, Object> condition;
	protected boolean distinct;
	protected String orderByClause;
	private Integer oracleStart;
	private Integer oracleEnd;

	protected Criteria(Criteria example)
	{
		orderByClause = example.orderByClause;
		condition = example.condition;
		distinct = example.distinct;
		oracleStart = example.oracleStart;
		oracleEnd = example.oracleEnd;
	}

	public Criteria()
	{
		condition = new HashMap();
	}

	public void clear()
	{
		condition.clear();
		orderByClause = null;
		distinct = false;
		oracleStart = null;
		oracleEnd = null;
	}

	public Criteria put(String condition, Object value)
	{
		this.condition.put(condition, value);
		return this;
	}

	public Object get(String key)
	{
		return condition.get(key);
	}

	public void setOrderByClause(String orderByClause)
	{
		this.orderByClause = orderByClause;
	}

	public void setDistinct(boolean distinct)
	{
		this.distinct = distinct;
	}

	public void setCondition(Map<String, Object> condition)
	{
		this.condition = condition;
	}

	public Map<String, Object> getCondition()
	{
		return condition;
	}

	public void setOracleStart(Integer oracleStart)
	{
		this.oracleStart = oracleStart;
	}

	public void setOracleEnd(Integer oracleEnd)
	{
		this.oracleEnd = oracleEnd;
	}

	public BigDecimal getAsBigDecimal(String key)
	{
		Object obj = TypeCaseHelper.convert(get(key), "BigDecimal", null);
		if (obj != null)
			return (BigDecimal)obj;
		else
			return null;
	}

	public Date getAsDate(String key)
	{
		Object obj = TypeCaseHelper.convert(get(key), "Date", "yyyy-MM-dd");
		if (obj != null)
			return (Date)obj;
		else
			return null;
	}

	public Integer getAsInteger(String key)
	{
		Object obj = TypeCaseHelper.convert(get(key), "Integer", null);
		if (obj != null)
			return (Integer)obj;
		else
			return null;
	}

	public Long getAsLong(String key)
	{
		Object obj = TypeCaseHelper.convert(get(key), "Long", null);
		if (obj != null)
			return (Long)obj;
		else
			return null;
	}

	public String getAsString(String key)
	{
		Object obj = TypeCaseHelper.convert(get(key), "String", null);
		if (obj != null)
			return (String)obj;
		else
			return "";
	}

	public Timestamp getAsTimestamp(String key)
	{
		Object obj = TypeCaseHelper.convert(get(key), "Timestamp", "yyyy-MM-dd HH:mm:ss");
		if (obj != null)
			return (Timestamp)obj;
		else
			return null;
	}

	public Boolean getAsBoolean(String key)
	{
		Object obj = TypeCaseHelper.convert(get(key), "Boolean", null);
		if (obj != null)
			return (Boolean)obj;
		else
			return null;
	}
}
