// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExtPager.java

package com.chenxin.authority.pojo;


// Referenced classes of package com.chenxin.authority.pojo:
//			Table

public class ExtPager
{

	private Integer limit;
	private Integer start;
	private String dir;
	private String sort;

	public ExtPager()
	{
	}

	public Integer getLimit()
	{
		return limit;
	}

	public void setLimit(Integer limit)
	{
		this.limit = limit;
	}

	public Integer getStart()
	{
		return start;
	}

	public void setStart(Integer start)
	{
		this.start = start;
	}

	public String getDir()
	{
		return dir;
	}

	public void setDir(String dir)
	{
		this.dir = dir;
	}

	public String getSort()
	{
		return Table.toClumn(sort);
	}

	public void setSort(String sort)
	{
		this.sort = sort;
	}
}
