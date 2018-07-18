package com.jianma.zeus.controller;

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
import com.jianma.zeus.model.Assignment;
import com.jianma.zeus.model.PageModel;
import com.jianma.zeus.model.ResultModel;
import com.jianma.zeus.service.AssignmentService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Controller
@RequestMapping(value="/assignment")
public class AssignmentController extends ZeusController {

	@Autowired
	@Qualifier(value = "assignmentServiceImpl")
	private  AssignmentService assignmentServiceImpl;
	
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/createAssignment", method = RequestMethod.POST)
	public ResultModel createAssignment(HttpServletRequest request, HttpServletResponse response, @RequestBody Assignment assignment){
		resultModel = new ResultModel();
		
		int result = assignmentServiceImpl.createAssignment(assignment);
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
	@RequestMapping(value="/updateAssignment", method = RequestMethod.POST)
	public ResultModel updateAssignment(HttpServletRequest request, HttpServletResponse response, @RequestBody Assignment assignment){
		resultModel = new ResultModel();
		int result = assignmentServiceImpl.updateAssignment(assignment);
		if (result == ResponseCodeUtil.DB_OPERATION_SUCCESS){
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		else{
			throw new ZeusException(500, "修改出错");
		}
		
	}
	
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/deleteAssignment/{id}", method = RequestMethod.GET)
	public ResultModel deleteAssignment(HttpServletRequest request, HttpServletResponse response, @PathVariable int id){
		resultModel = new ResultModel();
		int result = assignmentServiceImpl.deleteAssignment((long)id);
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
	@RequestMapping(value="/getAssignmentListByPage", method = RequestMethod.GET)
	public ResultModel getAssignmentListByPage(HttpServletRequest request, HttpServletResponse response, @RequestParam int offset, @RequestParam int limit){
		resultModel = new ResultModel();
		try{
			PageModel pageModel = assignmentServiceImpl.getAssignmentListByPage(limit, offset);
			resultModel.setObject(pageModel);
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
	@RequestMapping(value="/getAssignmentListByPageAndUserId", method = RequestMethod.GET)
	public ResultModel getAssignmentListByPageAndUserId(HttpServletRequest request, HttpServletResponse response, 
		@RequestParam int userId, @RequestParam int curriculumId, @RequestParam int offset, @RequestParam int limit){
		resultModel = new ResultModel();
		try{
			PageModel pageModel = assignmentServiceImpl.getAssignmentListByPageAndUserId(userId, curriculumId, limit, offset);
			resultModel.setObject(pageModel);
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
	@RequestMapping(value="/getAssignmentListByPageAndCurriculumId", method = RequestMethod.GET)
	public ResultModel getAssignmentListByPageAndCurriculumId(HttpServletRequest request, HttpServletResponse response, 
		@RequestParam int curriculumId, @RequestParam int offset, @RequestParam int limit){
		resultModel = new ResultModel();
		try{
			PageModel pageModel = assignmentServiceImpl.getAssignmentListByPageAndCurriculumId(curriculumId, limit, offset);
			resultModel.setObject(pageModel);
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
	@RequestMapping(value="/getAssignmentListByTagName", method = RequestMethod.GET)
	public ResultModel getAssignmentListByTagName(HttpServletRequest request, HttpServletResponse response, 
		@RequestParam String tagName, @RequestParam int offset, @RequestParam int limit){
		resultModel = new ResultModel();
		try{
			PageModel pageModel = assignmentServiceImpl.getAssignmentListByTagName(tagName, limit, offset);
			resultModel.setObject(pageModel);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "获取数据出错");
		}
	}
	
}
