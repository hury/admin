// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   TypeCastException.java

package com.chenxin.authority.common.utils;

import java.io.PrintStream;
import java.io.PrintWriter;

public class TypeCastException extends RuntimeException
{

	private static final long serialVersionUID = 1L;
	Throwable nested;

	public TypeCastException()
	{
		nested = null;
	}

	public TypeCastException(String msg)
	{
		super(msg);
		nested = null;
	}

	public TypeCastException(String msg, Throwable nested)
	{
		super(msg);
		this.nested = null;
		this.nested = nested;
	}

	public TypeCastException(Throwable nested)
	{
		this.nested = null;
		this.nested = nested;
	}

	public String getMessage()
	{
		if (nested != null)
			return (new StringBuilder()).append(super.getMessage()).append(" (").append(nested.getMessage()).append(")").toString();
		else
			return super.getMessage();
	}

	public String getNonNestedMessage()
	{
		return super.getMessage();
	}

	public Throwable getNested()
	{
		if (nested == null)
			return this;
		else
			return nested;
	}

	public void printStackTrace()
	{
		super.printStackTrace();
		if (nested != null)
			nested.printStackTrace();
	}

	public void printStackTrace(PrintStream ps)
	{
		super.printStackTrace(ps);
		if (nested != null)
			nested.printStackTrace(ps);
	}

	public void printStackTrace(PrintWriter pw)
	{
		super.printStackTrace(pw);
		if (nested != null)
			nested.printStackTrace(pw);
	}
}
