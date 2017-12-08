// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DB2Dialect.java

package com.chenxin.authority.common.mybatis.dialect;


// Referenced classes of package com.chenxin.authority.common.mybatis.dialect:
//			Dialect

public class DB2Dialect extends Dialect
{

	public DB2Dialect()
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

	private static String getRowNumber(String sql)
	{
		StringBuffer rownumber = (new StringBuffer(50)).append("rownumber() over(");
		int orderByIndex = sql.toLowerCase().indexOf("order by");
		if (orderByIndex > 0 && !hasDistinct(sql))
			rownumber.append(sql.substring(orderByIndex));
		rownumber.append(") as rownumber_,");
		return rownumber.toString();
	}

	private static boolean hasDistinct(String sql)
	{
		return sql.toLowerCase().indexOf("select distinct") >= 0;
	}

	public String getLimitString(String sql, int offset, String offsetPlaceholder, int limit, String limitPlaceholder)
	{
		int startOfSelect = sql.toLowerCase().indexOf("select");
		StringBuffer pagingSelect = (new StringBuffer(sql.length() + 100)).append(sql.substring(0, startOfSelect)).append("select * from ( select ").append(getRowNumber(sql));
		if (hasDistinct(sql))
			pagingSelect.append(" row_.* from ( ").append(sql.substring(startOfSelect)).append(" ) as row_");
		else
			pagingSelect.append(sql.substring(startOfSelect + 6));
		pagingSelect.append(" ) as temp_ where rownumber_ ");
		if (offset > 0)
		{
			String endString = (new StringBuilder()).append(offsetPlaceholder).append("+").append(limitPlaceholder).toString();
			pagingSelect.append((new StringBuilder()).append("between ").append(offsetPlaceholder).append("+1 and ").append(endString).toString());
		} else
		{
			pagingSelect.append((new StringBuilder()).append("<= ").append(limitPlaceholder).toString());
		}
		return pagingSelect.toString();
	}
}
