// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   Table.java

package com.chenxin.authority.pojo;


public class Table
{

	public Table()
	{
	}

	public static String toClumn(String field)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < field.length(); i++)
		{
			char c = field.charAt(i);
			if (Character.isUpperCase(c) && i > 0)
				sb.append("_").append(Character.toUpperCase(c));
			else
				sb.append(Character.toUpperCase(c));
		}

		return sb.toString();
	}
}
