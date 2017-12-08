// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExtGridReturn.java

package com.chenxin.authority.pojo;

import java.util.List;

public class ExtGridReturn
{

	private int results;
	private List<?> rows;

	public ExtGridReturn()
	{
	}

	public ExtGridReturn(int results, List rows)
	{
		this.results = results;
		this.rows = rows;
	}

	public int getResults()
	{
		return results;
	}

	public void setResults(int results)
	{
		this.results = results;
	}

	public List<?> getRows()
	{
		return rows;
	}

	public void setRows(List<?> rows)
	{
		this.rows = rows;
	}
}
