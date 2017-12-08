// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   PostgreSQLDialect.java

package com.chenxin.authority.common.mybatis.dialect;


// Referenced classes of package com.chenxin.authority.common.mybatis.dialect:
//			Dialect

public class PostgreSQLDialect extends Dialect
{

	public PostgreSQLDialect()
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
		return (new StringBuffer(sql.length() + 20)).append(sql).append(offset <= 0 ? (new StringBuilder()).append(" limit ").append(limitPlaceholder).toString() : (new StringBuilder()).append(" limit ").append(limitPlaceholder).append(" offset ").append(offsetPlaceholder).toString()).toString();
	}
}
