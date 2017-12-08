// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   UserController.java

package com.chenxin.authority.web.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.chenxin.authority.common.springmvc.DateConvertEditor;
import com.chenxin.authority.pojo.BaseUsers;
import com.chenxin.authority.pojo.Criteria;
import com.chenxin.authority.pojo.ExceptionReturn;
import com.chenxin.authority.pojo.ExtGridReturn;
import com.chenxin.authority.pojo.ExtPager;
import com.chenxin.authority.pojo.ExtReturn;
import com.chenxin.authority.service.BaseUserRoleService;
import com.chenxin.authority.service.BaseUsersService;

@Controller
@RequestMapping( { "/user" })
public class UserController {

	private static final Logger logger = LoggerFactory
			.getLogger(UserController.class);
	@Autowired
	private BaseUsersService baseUsersService;
	@Autowired
	private BaseUserRoleService baseUserRoleService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Date.class, new DateConvertEditor());
		binder
				.registerCustomEditor(String.class, new StringTrimmerEditor(
						true));
	}

	@RequestMapping(method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String user() {
		return "user/user";
	}

	@RequestMapping(method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public Object all(ExtPager pager,
			@RequestParam(required = false, defaultValue = "")
			String realName) {
		Criteria criteria = new Criteria();

		if ((pager.getLimit() != null) && (pager.getStart() != null)) {
			criteria.setOracleEnd(Integer.valueOf(pager.getLimit().intValue()
					+ pager.getStart().intValue()));
			criteria.setOracleStart(pager.getStart());
		}

		if ((StringUtils.isNotBlank(pager.getDir()))
				&& (StringUtils.isNotBlank(pager.getSort()))) {
			criteria.setOrderByClause(pager.getSort() + " " + pager.getDir());
		}
		if (StringUtils.isNotBlank(realName)) {
			criteria.put("realNameLike", realName);
		}
		List list = this.baseUsersService.selectByExample(criteria);
		int total = this.baseUsersService.countByExample(criteria);
		return new ExtGridReturn(total, list);
	}

	@RequestMapping(value = { "/changepwd" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String changePwd() {
		return "user/changepwd";
	}

	@RequestMapping(value = { "/changepwd" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public Object changePassword(@RequestParam
	String oldPassword, @RequestParam
	String newPassword, @RequestParam
	String comparePassword, @RequestParam
	String userId, HttpSession session) {
		try {
			if (StringUtils.isBlank(userId)) {
				return new ExtReturn(false, "用户ID不能为空！");
			}
			if (StringUtils.isBlank(oldPassword)) {
				return new ExtReturn(false, "原密码不能为空！");
			}
			if (StringUtils.isBlank(newPassword)) {
				return new ExtReturn(false, "新密码不能为空！");
			}
			if (StringUtils.isBlank(comparePassword)) {
				return new ExtReturn(false, "确认密码不能为空！");
			}
			if (!comparePassword.equals(newPassword)) {
				return new ExtReturn(false, "两次输入的密码不一致！");
			}
			BaseUsers user = (BaseUsers) session.getAttribute("CURRENT_USER");
			Criteria criteria = new Criteria();
			criteria.put("user", user);
			criteria.put("userId", userId);

			criteria.put("oldPassword", oldPassword);

			criteria.put("newPassword", newPassword);
			String result = this.baseUsersService.updateUserPassword(criteria);
			if ("01".equals(result)) {
				session.removeAttribute("CURRENT_USER");
				session.invalidate();
				return new ExtReturn(true, "修改密码成功！请重新登录！");
			}
			if ("00".equals(result)) {
				return new ExtReturn(false, "修改密码失败!");
			}
			return new ExtReturn(false, result);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping(value = { "/{userId}" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	@ResponseBody
	public Object myRole(@PathVariable
	String userId) {
		try {
			Criteria criteria = new Criteria();
			criteria.put("userId", userId);
			logger.debug("{}", userId);
			List list = this.baseUserRoleService.selectByExample(criteria);
			return list;
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping( { "/reset/{userId}" })
	@ResponseBody
	public Object resetPassword(@PathVariable
	String userId) {
		try {
			if (StringUtils.isBlank(userId)) {
				return new ExtReturn(false, "用户主键不能为空！");
			}
			Criteria criteria = new Criteria();
			criteria.put("userId", userId);
			String result = this.baseUsersService
					.resetPwdByPrimaryKey(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, "重置密码成功！");
			if ("00".equals(result)) {
				return new ExtReturn(false, "重置密码失败！");
			}
			return new ExtReturn(false, result);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping( { "/del/{userId}" })
	@ResponseBody
	public Object delete(@PathVariable
	String userId, HttpSession session) {
		try {
			if (StringUtils.isBlank(userId)) {
				return new ExtReturn(false, "用户主键不能为空！");
			}

			BaseUsers user = (BaseUsers) session.getAttribute("CURRENT_USER");
			if (userId.equals(user.getUserId())) {
				return new ExtReturn(false, "不能删除自己的帐号！");
			}
			Criteria criteria = new Criteria();
			criteria.put("userId", userId);
			String result = this.baseUsersService.deleteByPrimaryKey(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, "删除成功！");
			if ("00".equals(result)) {
				return new ExtReturn(false, "删除失败！");
			}
			return new ExtReturn(false, result);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping( { "/validate" })
	@ResponseBody
	public Object validateAccount(
			@RequestParam(value = "account", required = false, defaultValue = "")
			String account, @RequestParam
			String userId) {
		try {
			Criteria criteria = new Criteria();
			if (StringUtils.isNotBlank(account)) {
				criteria.put("account", account);
			}
			if (StringUtils.isNotBlank(userId)) {
				criteria.put("userId", userId);
			}
			String result = this.baseUsersService.validateAccount(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, "帐号未被注册！");
			if ("00".equals(result)) {
				return new ExtReturn(false, "帐号已经被注册！请重新填写!");
			}
			return new ExtReturn(false, result);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping( { "/save" })
	@ResponseBody
	public Object save(BaseUsers user, @RequestParam
	Collection<String> roleIds) {
		try {
			if ((roleIds == null) || (roleIds.size() == 0)) {
				return new ExtReturn(false, "请至少选择一个角色！");
			}
			if (StringUtils.isBlank(user.getAccount())) {
				return new ExtReturn(false, "帐号不能为空！");
			}
			Criteria criteria = new Criteria();
			criteria.put("roleIds", roleIds);
			criteria.put("user", user);
			String result = this.baseUsersService.saveUser(criteria);
			if ("01".equals(result))
				return new ExtReturn(true, "保存成功！");
			if ("00".equals(result)) {
				return new ExtReturn(false, "保存失败！");
			}
			return new ExtReturn(false, result);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}

	@RequestMapping(value = { "/myinfo" }, method = { org.springframework.web.bind.annotation.RequestMethod.GET })
	public String myinfo() {
		return "user/myinfo";
	}

	@RequestMapping(value = { "/myinfo" }, method = { org.springframework.web.bind.annotation.RequestMethod.POST })
	@ResponseBody
	public Object saveMyinfo(BaseUsers user) {
		try {
			if (user == null) {
				return new ExtReturn(false, "用户不能为空！");
			}
			if (StringUtils.isBlank(user.getUserId())) {
				return new ExtReturn(false, "用户ID不能为空！");
			}
			String result = this.baseUsersService
					.updateByPrimaryKeySelective(user);
			if ("01".equals(result))
				return new ExtReturn(true, "用户信息更新成功！请重新登录！");
			if ("00".equals(result)) {
				return new ExtReturn(false, "用户信息更新失败！");
			}
			return new ExtReturn(false, result);
		} catch (Exception e) {
			logger.error("Exception: ", e);
			return new ExceptionReturn(e);
		}
	}
}
