package com.chenxin.authority.web.interseptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class LoginInterceptor extends HandlerInterceptorAdapter
{

	public LoginInterceptor()
	{
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
		throws Exception
	{
		if (null == request.getSession().getAttribute("CURRENT_USER"))
		{
			String requestedWith = request.getHeader("x-requested-with");
			if (requestedWith != null && "XMLHttpRequest".equals(requestedWith))
			{
				response.setHeader("session-status", "timeout");
				response.getWriter().print("{\"error\":true,\"msg\":\"登录超时,请重新登录！\"}");
			} else
			{
				response.sendRedirect((new StringBuilder()).append(request.getContextPath()).append("/").toString());
			}
			return false;
		} else
		{
			return true;
		}
	}
}
