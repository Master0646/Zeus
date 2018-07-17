package com.jianma.zeus.controller;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jianma.zeus.ZeusController;
import com.jianma.zeus.exception.ZeusException;
import com.jianma.zeus.model.PageModel;
import com.jianma.zeus.model.ResultModel;
import com.jianma.zeus.model.UserCurriculum;
import com.jianma.zeus.service.UserCurriculumService;

@Controller
@RequestMapping(value="/userCurriculum")
public class UserCurriculumController extends ZeusController{

	@Autowired
	@Qualifier(value = "userCurriculumServiceImpl")
	private UserCurriculumService userCurriculumServiceImpl;
	
	@RequiresRoles(value ={"学生"})
	@ResponseBody
	@RequestMapping(value="/createUserCurriculum", method = RequestMethod.POST)
	public ResultModel createUserCurriculum(HttpServletRequest request, HttpServletResponse response, @RequestBody UserCurriculum userCurriculum){
		resultModel = new ResultModel();
		try{
			userCurriculumServiceImpl.createUserCurriculum(userCurriculum);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "创建出错");
		}
	}
	
	@RequiresRoles(value ={"学生"})
	@ResponseBody
	@RequestMapping(value="/updateUserCurriculum", method = RequestMethod.POST)
	public ResultModel updateUserCurriculum(HttpServletRequest request, HttpServletResponse response, @RequestBody UserCurriculum userCurriculum){
		resultModel = new ResultModel();
		try{
			userCurriculumServiceImpl.updateUserCurriculum(userCurriculum);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "创建出错");
		}
	}
	
	@RequiresRoles(value ={"学生"})
	@ResponseBody
	@RequestMapping(value="/deleteUserCurriculum/{id}", method = RequestMethod.DELETE)
	public ResultModel deleteUserCurriculum(HttpServletRequest request, HttpServletResponse response,  @PathVariable int id){
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
	@RequestMapping(value="/getCurriculumByPage", method = RequestMethod.GET)
	public ResultModel getCurriculumByPage(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam int limit, @RequestParam int offset){
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
	@RequestMapping(value="/getUserCurriculumByUserId", method = RequestMethod.GET)
	public ResultModel getUserCurriculumByUserId(HttpServletRequest request, HttpServletResponse response, 
			@RequestParam int userId, @RequestParam int limit, @RequestParam int offset){
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

}
