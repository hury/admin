// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   BaseModules.java

package com.chenxin.authority.pojo;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnore;

public class BaseModules
	implements Serializable
{

	private static final long serialVersionUID = 1L;
	private Integer moduleId;
	private String moduleName;
	private String moduleUrl;
	private Integer parentId;
	private Short leaf;
	private Short expanded;
	private Short displayIndex;
	private Short isDisplay;
	private String enModuleName;
	private String iconCss;
	private String information;

	public BaseModules()
	{
	}

	public Integer getModuleId()
	{
		return moduleId;
	}

	public void setModuleId(Integer moduleId)
	{
		this.moduleId = moduleId;
	}

	public String getModuleName()
	{
		return moduleName;
	}

	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}

	public String getModuleUrl()
	{
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl)
	{
		this.moduleUrl = moduleUrl;
	}

	public Integer getParentId()
	{
		return parentId;
	}

	public void setParentId(Integer parentId)
	{
		this.parentId = parentId;
	}

	public Short getLeaf()
	{
		return leaf;
	}

	public void setLeaf(Short leaf)
	{
		this.leaf = leaf;
	}

	public Short getExpanded()
	{
		return expanded;
	}

	public void setExpanded(Short expanded)
	{
		this.expanded = expanded;
	}

	public Short getDisplayIndex()
	{
		return displayIndex;
	}

	public void setDisplayIndex(Short displayIndex)
	{
		this.displayIndex = displayIndex;
	}

	public Short getIsDisplay()
	{
		return isDisplay;
	}

	public void setIsDisplay(Short isDisplay)
	{
		this.isDisplay = isDisplay;
	}

    @JsonIgnore
	public String getEnModuleName()
	{
		return enModuleName;
	}

	public void setEnModuleName(String enModuleName)
	{
		this.enModuleName = enModuleName;
	}

	public String getIconCss()
	{
		return iconCss;
	}

	public void setIconCss(String iconCss)
	{
		this.iconCss = iconCss;
	}

	public String getInformation()
	{
		return information;
	}

	public void setInformation(String information)
	{
		this.information = information;
	}
}
