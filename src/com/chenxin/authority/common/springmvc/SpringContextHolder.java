// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   SpringContextHolder.java

package com.chenxin.authority.common.springmvc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class SpringContextHolder
	implements ApplicationContextAware
{

	private static ApplicationContext applicationContext;

	public SpringContextHolder()
	{
	}

	public void setApplicationContext(ApplicationContext applicationContexts)
	{
		applicationContext = applicationContexts;
	}

	public static ApplicationContext getApplicationContext()
	{
		checkApplicationContext();
		return applicationContext;
	}

	public static Object getBean(String name)
	{
		checkApplicationContext();
		return applicationContext.getBean(name);
	}

	public static Object getBean(Class<?> clazz)
	{
		checkApplicationContext();
		return applicationContext.getBeansOfType(clazz);
	}

	public static void cleanApplicationContext()
	{
		applicationContext = null;
	}

	private static void checkApplicationContext()
	{
		if (applicationContext == null){
			throw new IllegalStateException("applicaitonContext未注入,请在applicationContext.xml中定义SpringContextHolder");
		}else{
			return;
		}
	}
}
