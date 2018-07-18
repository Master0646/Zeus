package com.jianma.zeus;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.ExpiredCredentialsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value = "/dologin")
	public String doLogin(HttpServletRequest request, Model model) {
		String msg = "";
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
			if (subject.isAuthenticated()) {

				try {
					subject.checkRole("管理员");
					return "redirect:/news/newsMgr";
				} catch (AuthorizationException e) {
					try {
						subject.checkRole("评委");
						return "redirect:/review/judgeIndex";
					} catch (AuthorizationException ex) {
						return "redirect:/production/works";
					}

				}
			} else {
				return "/frontend/login";
			}
		} catch (IncorrectCredentialsException e) {
			msg = "登录密码错误.";
			model.addAttribute("error", msg);
			System.out.println(msg);
		} catch (ExcessiveAttemptsException e) {
			msg = "登录失败次数过多";
			model.addAttribute("error", msg);
			System.out.println(msg);
		} catch (LockedAccountException e) {
			msg = "帐号已被锁定.";
			model.addAttribute("error", msg);
			System.out.println(msg);
		} catch (DisabledAccountException e) {
			msg = "帐号已被禁用. ";
			model.addAttribute("error", msg);
			System.out.println(msg);
		} catch (ExpiredCredentialsException e) {
			msg = "帐号已过期.";
			model.addAttribute("error", msg);
			System.out.println(msg);
		} catch (UnknownAccountException e) {
			msg = "帐号不存在.或者未激活";
			model.addAttribute("error", msg);
			System.out.println(msg);
		} catch (UnauthorizedException e) {
			msg = "您没有得到相应的授权！";
			model.addAttribute("error", msg);
			System.out.println(msg);
		}
		return "/frontend/login";
	}
}
