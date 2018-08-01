package com.jianma.zeus.controller;

import java.util.Optional;
import java.util.Set;

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
import com.jianma.zeus.exception.ServerException;
import com.jianma.zeus.exception.ZeusException;
import com.jianma.zeus.model.PageModel;
import com.jianma.zeus.model.ResultModel;
import com.jianma.zeus.model.User;
import com.jianma.zeus.service.UserService;

@Controller
@RequestMapping(value="/user")
public class UserController extends ZeusController {

	@Autowired
	@Qualifier(value = "userServiceImpl")
	private  UserService userServiceImpl;
	
	
	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/userManage")
	public String userManage(HttpServletRequest request, Model model) {
		return "admin/userManage";
	}

	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/alterUser")
	public String alterSchool(HttpServletRequest request, Model model) {
		return "admin/alterUser";
	}
	
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/createUser", method = RequestMethod.POST)
	public ResultModel createUser(HttpServletRequest request, HttpServletResponse response, @RequestBody User user){
		resultModel = new ResultModel();
		try{
			
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "创建出错");
		}
	}
	
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/updateUser", method = RequestMethod.POST)
	public ResultModel updateUser(HttpServletRequest request, HttpServletResponse response, @RequestBody User user){
		resultModel = new ResultModel();
		try{
			
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "创建出错");
		}
	}
	
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/deleteUser/{id}", method = RequestMethod.DELETE)
	public ResultModel deleteUser(HttpServletRequest request, HttpServletResponse response, @PathVariable int id){
		resultModel = new ResultModel();
		try{
			
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "创建出错");
		}
	}
	
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/correlationRoles", method = RequestMethod.POST)
	public ResultModel correlationRoles(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam int userId, @RequestParam String roleIds){
		resultModel = new ResultModel();
		try{
			
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "创建出错");
		}
	}
	
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/uncorrelationRoles", method = RequestMethod.POST)
	public ResultModel uncorrelationRoles(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam int userId, @RequestParam String roleIds){
		resultModel = new ResultModel();
		try{
			
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "创建出错");
		}
	}


	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/findOne", method = RequestMethod.GET)
	public ResultModel findOne(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam int userId){
		resultModel = new ResultModel();
		try{
			resultModel.setObject(userServiceImpl.findOne((long)userId));
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "创建出错");
		}
	}
	
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/findByEmail", method = RequestMethod.GET)
	public ResultModel findByEmail(HttpServletRequest request, HttpServletResponse response, 
			 @RequestParam String email){
		resultModel = new ResultModel();
		try{
			resultModel.setObject(userServiceImpl.findByEmail(email));
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "创建出错");
		}
	}

	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/findRoles", method = RequestMethod.GET)
	public ResultModel findRoles(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam int userId, @RequestParam String email){
		resultModel = new ResultModel();
		try{
			resultModel.setObject(userServiceImpl.findRoles(email));
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "创建出错");
		}
	}
	
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/findPermissions", method = RequestMethod.GET)
	public ResultModel findPermissions(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam String email){
		resultModel = new ResultModel();
		try{
			resultModel.setObject(userServiceImpl.findPermissions(email));
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "创建出错");
		}
	}


	@ResponseBody
	@RequestMapping(value = "/resetLoginUserPwd", method = RequestMethod.POST)
	public ResultModel resetLoginUserPwd(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String newPwd) {
		resultModel = new ResultModel();
		try {
			userServiceImpl.resetLoginUserPwd(newPwd);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new ZeusException(500, "修改出错");
		}
	}

	@ResponseBody
	@RequestMapping(value = "/getUserByPage", method = RequestMethod.POST)
	public ResultModel getUserByPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int offset, @RequestParam int limit) {

		ResultModel resultModel = new ResultModel();
		try {
			PageModel pagingModel = userServiceImpl.getUserByPage(offset, limit);
			resultModel.setObject(pagingModel.getList());
			resultModel.setCountData(pagingModel.getCount());
			resultModel.setSuccess(true);
			resultModel.setResultCode(200);
		} catch (Exception e) {
			resultModel.setSuccess(false);
		}
		return resultModel;
	}


	@ResponseBody
	@RequestMapping(value = "/resetUserValid", method = RequestMethod.POST)
	public ResultModel resetUserValid(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String email, @RequestParam int valid) {
		resultModel = new ResultModel();
		try {
			userServiceImpl.updateValidSign(email, valid);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new ZeusException(500, "修改出错");
		}
	}
}
