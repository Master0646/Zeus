package com.jianma.zeus.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;

import com.jianma.zeus.ZeusController;
import com.jianma.zeus.exception.ZeusException;
import com.jianma.zeus.model.Menu;
import com.jianma.zeus.model.ResultModel;
import com.jianma.zeus.service.MenuService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Controller
@RequestMapping(value = "/menu")
public class MenuController extends ZeusController{

	@Autowired
	@Qualifier(value = "menuServiceImpl")
	private MenuService menuServiceImpl;
	
	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/menuManage")
	public String menuManage(HttpServletRequest request, Model model) {
		return "admin/menuManage";
	}

	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/alterMenu")
	public String alterMenu(HttpServletRequest request, Model model) {
		return "admin/alterMenu";
	}
	
	@RequiresRoles(value = { "" })
	@ResponseBody
	@RequestMapping(value = "/createMenu", method = RequestMethod.POST)
	public ResultModel createMenu(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Menu menu) {
		resultModel = new ResultModel();
		int result = menuServiceImpl.createMenu(menu);
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
	@RequestMapping(value = "/updateMenu", method = RequestMethod.POST)
	public ResultModel updateMenu(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Menu menu) {
		resultModel = new ResultModel();
		int result = menuServiceImpl.updateMenu(menu);
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
	@RequestMapping(value = "/deleteMenu/{id}", method = RequestMethod.DELETE)
	public ResultModel deleteMenu(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int id) {
		resultModel = new ResultModel();
		int result = menuServiceImpl.deleteMenu((long)id);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS){
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		else{
			throw new ZeusException(500, "删除出错");
		}
	}

	@RequiresRoles(value = { "" })
	@ResponseBody
	@RequestMapping(value = "/getMenuList", method = RequestMethod.GET)
	public ResultModel getMenuList(HttpServletRequest request, HttpServletResponse response) {
		resultModel = new ResultModel();
		try {
			List<Menu> list = menuServiceImpl.getMenuList();
			resultModel.setObject(list);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new ZeusException(500, "获取数据出错");
		}
	}

}
