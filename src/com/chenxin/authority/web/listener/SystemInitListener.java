package com.chenxin.authority.web.listener;

import com.chenxin.authority.common.springmvc.SpringContextHolder;
import com.chenxin.authority.pojo.Criteria;
import com.chenxin.authority.service.BaseFieldsService;
import javax.servlet.*;

public class SystemInitListener
	implements ServletContextListener
{

	public SystemInitListener()
	{
	}

	public void contextInitialized(ServletContextEvent sce)
	{
		ServletContext servletContext = sce.getServletContext();
		BaseFieldsService baseFieldsService = (BaseFieldsService)SpringContextHolder.getBean("baseFieldsServiceImpl");
		Criteria criteria = new Criteria();
		criteria.setOrderByClause(" field desc ,sort asc ");
		criteria.put("enabled", "1");
		servletContext.setAttribute("fields", baseFieldsService.selectAllByExample(criteria));
	}

	public void contextDestroyed(ServletContextEvent servletcontextevent)
	{
	}
}
