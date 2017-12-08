// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   HomeController.java

package com.chenxin.authority.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{

	public HomeController()
	{
	}
	@RequestMapping({"/"})
	public String home()
	{
		return "login";
	}
	@RequestMapping({"/main"})
	public String main()
	{
		return "main";
	}
	@RequestMapping({"/header"})
	public String header()
	{
		return "header";
	}
	@RequestMapping({"/welcome"})
	public String welcome()
	{
		return "welcome";
	}
}
