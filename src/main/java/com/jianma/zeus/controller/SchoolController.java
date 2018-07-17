package com.jianma.zeus.controller;

import java.util.List;

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
import com.jianma.zeus.model.ResultModel;
import com.jianma.zeus.model.School;
import com.jianma.zeus.service.SchoolService;

@Controller
@RequestMapping(value="/school")
public class SchoolController extends ZeusController {

	@Autowired
	@Qualifier(value = "schoolServiceImpl")
	private  SchoolService schoolServiceImpl;
	
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/createSchool", method = RequestMethod.POST)
	public ResultModel createSchool(HttpServletRequest request, HttpServletResponse response, @RequestBody School school){
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
	@RequestMapping(value="/updateSchool", method = RequestMethod.POST)
	public ResultModel updateSchool(HttpServletRequest request, HttpServletResponse response, @RequestBody School school){
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
	@RequestMapping(value="/deleteSchool/{id}", method = RequestMethod.DELETE)
	public ResultModel deleteSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable int id){
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
	@RequestMapping(value="/getSchoolByProvince", method = RequestMethod.GET)
	public ResultModel getSchoolByProvince(HttpServletRequest request, HttpServletResponse response, @RequestParam String province){
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
	@RequestMapping(value="/getAcademyBySchoolName", method = RequestMethod.GET)
	public ResultModel getAcademyBySchoolName(HttpServletRequest request, HttpServletResponse response, @RequestParam String schoolName){
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
