// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DateConvertEditor.java

package com.chenxin.authority.common.springmvc;

import java.beans.PropertyEditorSupport;
import java.text.*;
import org.apache.commons.lang.StringUtils;

public class DateConvertEditor extends PropertyEditorSupport
{

	private DateFormat format;

	public DateConvertEditor()
	{
		format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	}

	public DateConvertEditor(String format)
	{
		this.format = new SimpleDateFormat(format);
	}

	public String getAsText()
	{
		if (getValue() == null)
			return "";
		else
			return format.format(getValue());
	}

	public void setAsText(String text)
		throws IllegalArgumentException
	{
		if (!StringUtils.isNotBlank(text))
			setValue(null);
		else
			try
			{
				setValue(format.parse(text));
			}
			catch (ParseException e)
			{
				throw new IllegalArgumentException("不能被转换的日期字符串，请检查!", e);
			}
	}
}
