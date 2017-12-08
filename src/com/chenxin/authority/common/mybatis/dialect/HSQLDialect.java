// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HSQLDialect.java

package com.chenxin.authority.common.mybatis.dialect;


// Referenced classes of package com.chenxin.authority.common.mybatis.dialect:
//			Dialect

public class HSQLDialect extends Dialect
{

	public HSQLDialect()
	{
	}

	public boolean supportsLimit()
	{
		return true;
	}

	public boolean supportsLimitOffset()
	{
		return true;
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
	{
		boolean hasOffset = offset > 0;
		return (new StringBuffer(sql.length() + 10)).append(sql).insert(sql.toLowerCase().indexOf("select") + 6, hasOffset ? (new StringBuilder()).append(" limit ").append(offsetPlaceholder).append(" ").append(limitPlaceholder).toString() : (new StringBuilder()).append(" top ").append(limitPlaceholder).toString()).toString();
	}
}
