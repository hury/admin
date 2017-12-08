// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SQLServerDialect.java

package com.chenxin.authority.common.mybatis.dialect;


// Referenced classes of package com.chenxin.authority.common.mybatis.dialect:
//			Dialect

public class SQLServerDialect extends Dialect
{

	public SQLServerDialect()
	{
	}

	public boolean supportsLimitOffset()
	{
		return false;
	}

	public boolean supportsLimit()
	{
		return true;
	}

	static int getAfterSelectInsertPoint(String sql)
	{
		int selectIndex = sql.toLowerCase().indexOf("select");
		int selectDistinctIndex = sql.toLowerCase().indexOf("select distinct");
		return selectIndex + (selectDistinctIndex != selectIndex ? 6 : 15);
	}

	public String getLimitString(String sql, int offset, int limit)
	{
		return getLimitString(sql, offset, null, limit, null);
	}

	public String getLimitString(String querySelect, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
	{
		if (offset > 0)
			throw new UnsupportedOperationException("sql server has no offset");
		else
			return (new StringBuffer(querySelect.length() + 8)).append(querySelect).insert(getAfterSelectInsertPoint(querySelect), (new StringBuilder()).append(" top ").append(limit).toString()).toString();
	}
}
