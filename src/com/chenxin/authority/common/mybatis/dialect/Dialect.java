// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Dialect.java

package com.chenxin.authority.common.mybatis.dialect;


public class Dialect
{

	public Dialect()
	{
	}

	public boolean supportsLimit()
	{
		return false;
	}

	public boolean supportsLimitOffset()
	{
		return supportsLimit();
	}

	public String getLimitString(String sql, int offset, int limit)
	{
		return getLimitString(sql, offset, Integer.toString(offset), limit, Integer.toString(limit));
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
	{
		throw new UnsupportedOperationException("paged queries not supported");
	}
}
