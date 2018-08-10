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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jianma.zeus.ZeusController;
import com.jianma.zeus.exception.ServerException;
import com.jianma.zeus.exception.ZeusException;
import com.jianma.zeus.model.PageModel;
import com.jianma.zeus.model.ResultModel;
import com.jianma.zeus.model.School;
import com.jianma.zeus.service.SchoolService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Controller
@RequestMapping(value="/school")
public class SchoolController extends ZeusController {

	@Autowired
	@Qualifier(value = "schoolServiceImpl")
	private  SchoolService schoolServiceImpl;
	
	@RequestMapping(value = "/{page}")
	public ModelAndView school(HttpServletRequest request, Model model, @PathVariable int page) {
		try {
			ModelAndView modelView = new ModelAndView();
			modelView.setViewName("frontend/school");
			modelView.addObject(null);
			return modelView;
		} catch (Exception e) {
			throw new ServerException(400, "服务器内部出错了");
		}
	}
	
	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/schoolManage")
	public String schoolManage(HttpServletRequest request, Model model) {
		return "admin/schoolManage";
	}

	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/alterSchool/{id}")
	public ModelAndView alterSchool(HttpServletRequest request, Model model,@PathVariable int id) {
		ModelAndView modelView = new ModelAndView();
		modelView.setViewName("admin/alterSchool");
		if (id > 0){
			School school = schoolServiceImpl.loadSchoolById(id);
			modelView.addObject("school",school);
		}
		return modelView;
	}
	
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/createSchool", method = RequestMethod.POST)
	public ResultModel createSchool(HttpServletRequest request, HttpServletResponse response, @RequestBody School school){
		resultModel = new ResultModel();
		
		int result = schoolServiceImpl.createSchool(school);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS){
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		else{
			throw new ZeusException(500, "创建出错");
		}
	}
	
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/updateSchool", method = RequestMethod.POST)
	public ResultModel updateSchool(HttpServletRequest request, HttpServletResponse response, @RequestBody School school){
		resultModel = new ResultModel();
		int result = schoolServiceImpl.updateSchool(school);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS){
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		else{
			throw new ZeusException(500, "更新出错");
		}
		
	}

	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/deleteSchool/{id}", method = RequestMethod.DELETE)
	public ResultModel deleteSchool(HttpServletRequest request, HttpServletResponse response, @PathVariable int id){
		resultModel = new ResultModel();
		int result = schoolServiceImpl.deleteSchool((long)id);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS){
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		else{
			throw new ZeusException(500, "删除出错");
		}
	}  


	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/getSchoolByProvince", method = RequestMethod.GET)
	public ResultModel getSchoolByProvince(HttpServletRequest request, HttpServletResponse response, @RequestParam String province){
		resultModel = new ResultModel();
		try{
			List<School> list = schoolServiceImpl.getSchoolByProvince(province);
			resultModel.setObject(list);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "获取数据出错");
		}
	} 
	  
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/getAcademyBySchoolId", method = RequestMethod.GET)
	public ResultModel getAcademyBySchoolId(HttpServletRequest request, HttpServletResponse response, @RequestParam int schoolId){
		resultModel = new ResultModel();
		try{
			List<School> list = schoolServiceImpl.getAcademyBySchoolId(schoolId);
			resultModel.setObject(list);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "获取数据出错");
		}
	}

	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/getAllSchool", method = RequestMethod.GET)
	public ResultModel getAllSchool(HttpServletRequest request, HttpServletResponse response){
		resultModel = new ResultModel();
		try{
			List<School> list = schoolServiceImpl.getAllSchool();
			resultModel.setObject(list);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "获取数据出错");
		}
	}
	
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/getSchoolByPage", method = RequestMethod.GET)
	public ResultModel getSchoolByPage(HttpServletRequest request, HttpServletResponse response, @RequestParam int limit,
			@RequestParam int offset){
		resultModel = new ResultModel();
		try{
			PageModel pModel = schoolServiceImpl.getSchoolByPage(limit, offset);
			resultModel.setObject(pModel);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "获取数据出错");
		}
	}
}
