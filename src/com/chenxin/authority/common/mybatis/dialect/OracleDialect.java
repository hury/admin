// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   OracleDialect.java

package com.chenxin.authority.common.mybatis.dialect;


// Referenced classes of package com.chenxin.authority.common.mybatis.dialect:
//			Dialect

public class OracleDialect extends Dialect
{

	public OracleDialect()
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
		sql = sql.trim();
		boolean isForUpdate = false;
		if (sql.toLowerCase().endsWith(" for update"))
		{
			sql = sql.substring(0, sql.length() - 11);
			isForUpdate = true;
		}
		StringBuffer pagingSelect = new StringBuffer(sql.length() + 100);
		if (offset > 0)
			pagingSelect.append("select * from ( select row_.*, rownum rownum_ from ( ");
		else
			pagingSelect.append("select * from ( ");
		pagingSelect.append(sql);
		if (offset > 0)
		{
			String endString = (new StringBuilder()).append(offsetPlaceholder).append("+").append(limitPlaceholder).toString();
			pagingSelect.append((new StringBuilder()).append(" ) row_ ) where rownum_ <= ").append(endString).append(" and rownum_ > ").append(offsetPlaceholder).toString());
		} else
		{
			pagingSelect.append((new StringBuilder()).append(" ) where rownum <= ").append(limitPlaceholder).toString());
		}
		if (isForUpdate)
			pagingSelect.append(" for update");
		return pagingSelect.toString();
	}
}
