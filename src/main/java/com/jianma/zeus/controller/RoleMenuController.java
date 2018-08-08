package com.jianma.zeus.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jianma.zeus.ZeusController;
import com.jianma.zeus.exception.ZeusException;
import com.jianma.zeus.model.RoleMenu;
import com.jianma.zeus.model.Menu;
import com.jianma.zeus.model.ResultModel;
import com.jianma.zeus.service.RoleMenuService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Controller
@RequestMapping(value = "/roleMenu")
public class RoleMenuController extends ZeusController{

	@Autowired
	@Qualifier(value = "roleMenuServiceImpl")
	private RoleMenuService roleMenuServiceImpl;
	
	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/roleMenuManage")
	public String roleMenuManage(HttpServletRequest request, Model model) {
		return "admin/roleMenuManage";
	}

	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/alterRoleMenu/{id}")
	public ModelAndView alterRoleMenu(HttpServletRequest request, Model model, @PathVariable int id) {
		ModelAndView modelView = new ModelAndView();
		if (id > 0){
			RoleMenu roleMenu = roleMenuServiceImpl.loadRoleMenuById(id);
			modelView.addObject("roleMenu", roleMenu);
		}
		modelView.setViewName("admin/alterRoleMenu");
		return modelView;
	}
	
	@RequiresRoles(value = { "" })
	@ResponseBody
	@RequestMapping(value = "/createRoleMenu", method = RequestMethod.POST)
	public ResultModel createRoleMenu(HttpServletRequest request, HttpServletResponse response,
			@RequestBody RoleMenu RoleMenu) {
		resultModel = new ResultModel();
		int result = roleMenuServiceImpl.createRoleMenu(RoleMenu);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS){
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		else{
			throw new ZeusException(500, "创建出错");
		}
	}

	@RequiresRoles(value = { "" })
	@ResponseBody
	@RequestMapping(value = "/updateRoleMenu", method = RequestMethod.POST)
	public ResultModel updateRoleMenu(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int roleId, @RequestParam String menuIds) {
		resultModel = new ResultModel();
		int[] a = Arrays.stream(menuIds.split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
		List<Integer> menuIdList = Arrays.stream(a).boxed().collect(Collectors.toList());
		int result = roleMenuServiceImpl.updateRoleMenu((long)roleId,menuIdList);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS){
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		else{
			throw new ZeusException(500, "更新出错");
		}
	}
	
	@RequiresRoles(value = { "" })
	@ResponseBody
	@RequestMapping(value = "/getRoleMenuList", method = RequestMethod.GET)
	public ResultModel getRoleMenuList(HttpServletRequest request, HttpServletResponse response,@RequestParam String roleIds) {
		resultModel = new ResultModel();
		try {
			int[] a = Arrays.stream(roleIds.split(",")).mapToInt(s -> Integer.parseInt(s)).toArray();
			List<Integer> roleIdList = Arrays.stream(a).boxed().collect(Collectors.toList());
			List<Menu> list = roleMenuServiceImpl.getMenuListByRoleId(roleIdList);
			resultModel.setObject(list);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new ZeusException(500, "获取数据出错");
		}
	}
}
