// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SybaseDialect.java

package com.chenxin.authority.common.mybatis.dialect;


// Referenced classes of package com.chenxin.authority.common.mybatis.dialect:
//			Dialect

public class SybaseDialect extends Dialect
{

	public SybaseDialect()
	{
	}

	public boolean supportsLimit()
	{
		return false;
	}

	public boolean supportsLimitOffset()
	{
		return false;
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
	{
		throw new UnsupportedOperationException("paged queries not supported");
	}
}
