package com.jianma.zeus.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jianma.zeus.ZeusController;
import com.jianma.zeus.exception.ZeusException;
import com.jianma.zeus.model.ResultModel;
import com.jianma.zeus.model.Role;
import com.jianma.zeus.service.RoleService;

@Controller
@RequestMapping(value = "/role")
public class RoleController extends ZeusController{

	
	@Autowired
	@Qualifier(value = "roleServiceImpl")
	private  RoleService roleServiceImpl;
	
	@RequiresRoles(value = { "" })
	@ResponseBody
	@RequestMapping(value = "/getAllRoles", method = RequestMethod.GET)
	public ResultModel getAllRoles(HttpServletRequest request, HttpServletResponse response) {
		resultModel = new ResultModel();
		try {
			List<Role> list = roleServiceImpl.getAllRoles();
			resultModel.setObject(list);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new ZeusException(500, "获取数据出错");
		}
	}
}
