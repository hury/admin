// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExceptionReturn.java

package com.chenxin.authority.pojo;

import java.io.PrintWriter;
import java.io.StringWriter;

public class ExceptionReturn
{

	private boolean success;
	private Object exceptionMessage;

	public ExceptionReturn()
	{
	}

	public ExceptionReturn(Throwable exceptionMessage)
	{
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		exceptionMessage.printStackTrace(pw);
		success = false;
		this.exceptionMessage = exceptionMessage.getMessage();
	}

	public boolean isSuccess()
	{
		return success;
	}

	public void setSuccess(boolean success)
	{
		this.success = success;
	}

	public Object getExceptionMessage()
	{
		return exceptionMessage;
	}

	public void setExceptionMessage(Object exceptionMessage)
	{
		this.exceptionMessage = exceptionMessage;
	}
}
