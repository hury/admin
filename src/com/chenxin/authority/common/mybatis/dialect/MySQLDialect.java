// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   MySQLDialect.java

package com.chenxin.authority.common.mybatis.dialect;


// Referenced classes of package com.chenxin.authority.common.mybatis.dialect:
//			Dialect

public class MySQLDialect extends Dialect
{

	public MySQLDialect()
	{
	}

	public boolean supportsLimitOffset()
	{
		return true;
	}

	public boolean supportsLimit()
	{
		return true;
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
	{
		if (offset > 0)
			return (new StringBuilder()).append(sql).append(" limit ").append(offsetPlaceholder).append(",").append(limitPlaceholder).toString();
		else
			return (new StringBuilder()).append(sql).append(" limit ").append(limitPlaceholder).toString();
	}
}
