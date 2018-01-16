package com.yonyou.gtmc.demo_f4.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javalite.activejdbc.LazyList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yonyou.f4.mvc.annotation.Conn;
import com.yonyou.f4.mvc.annotation.Txn;
import com.yonyou.f4.mvc.annotation.TxnConn;
import com.yonyou.f4.mvc.context.ActionContext;
import com.yonyou.f4.mvc.controller.BaseController;
import com.yonyou.gtmc.demo_f4.model.User;

@Controller
@TxnConn
@RequestMapping(value = "/user")
public class UserController extends BaseController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	/**
	 * 跳转到用户管理主页面
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/main")
	public ModelAndView main(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();

		try {
			mav.setViewName("user/main.html");
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			mav.addObject("exception", e);

			mav.addObject("msg", e.getMessage());
			mav.setViewName("error");
		}

		return mav;
	}

	/**
	 * 使用默认的事务管理和数据源
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ActionContext ac = ActionContext.getActionContext(request, response);

		try {
			String name = ac.getValue("name", String.class, null);
			String lastname = ac.getValue("lastname", String.class, null);
			String phone = ac.getValue("phone", String.class, null);
			String email = ac.getValue("email", String.class, null);

			LazyList<User> list = User.findBySQL("select SEQ_USER.nextval as id from dual", null);
			User user = list.get(0);
			user.set("id", user.getLongId());
			user.set("name", name);
			user.set("lastname", lastname);
			user.set("phone", phone);
			user.set("email", email);
			boolean success = user.insert();
			mav.addObject("data", user);
			mav.addObject("success", success);

		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			mav.addObject("exception", e);

			mav.addObject("msg", e.getMessage());
			mav.setViewName("error");
		}

		return mav;
	}

	/**
	 * 指定事务管理和数据源
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/delete")
	public ModelAndView delete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ActionContext ac = ActionContext.getActionContext(request, response);

		try {
			Long id = ac.getValue("id", Long.class, null);
			if (id != null) {
				int cnt = User.delete("id = ?", id);
				if (cnt > 0) {
					mav.addObject("success", id);
				}
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			mav.addObject("exception", e);

			mav.addObject("msg", e.getMessage());
			mav.setViewName("error");
		}

		return mav;
	}

	/**
	 * 指定数据源
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/update")
	public ModelAndView update(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ActionContext ac = ActionContext.getActionContext(request, response);

		try {
			Long id = ac.getValue("id", Long.class, null);
			String name = ac.getValue("name", String.class, null);
			String lastname = ac.getValue("lastname", String.class, null);
			String phone = ac.getValue("phone", String.class, null);
			String email = ac.getValue("email", String.class, null);

			if (id != null) {
				User user = User.findFirst("id = ?", id);
				if (user != null) {
					user.set("name", name);
					user.set("lastname", lastname);
					user.set("phone", phone);
					user.set("email", email);
					user.saveIt();

					mav.addObject("success", true);
				}
			}
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			mav.addObject("exception", e);

			mav.addObject("msg", e.getMessage());
			mav.setViewName("error");
		}

		return mav;
	}

	@RequestMapping(value = "/query")
	public ModelAndView query(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView();
		ActionContext ac = ActionContext.getActionContext(request, response);

		try {
			LazyList<User> list = User.findAll();
			int total = list.size();
			mav.addObject("success", true);
			mav.addObject("total", total);
			mav.addObject("rows", list.toMaps());
		} catch (Throwable e) {
			logger.error(e.getMessage(), e);
			mav.addObject("exception", e);
			mav.addObject("msg", e.getMessage());
			mav.setViewName("error");
		}

		return mav;
	}

}
