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
import com.jianma.zeus.model.Comment;
import com.jianma.zeus.model.PageModel;
import com.jianma.zeus.model.ResultModel;
import com.jianma.zeus.service.CommentService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Controller
@RequestMapping(value="/comment")
public class CommentController extends ZeusController {

	@Autowired
	@Qualifier(value = "commentServiceImpl")
	private  CommentService commentServiceImpl;
	
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/createComment", method = RequestMethod.POST)
	public ResultModel createComment(HttpServletRequest request, HttpServletResponse response, @RequestBody Comment comment){
		resultModel = new ResultModel();
		int result = commentServiceImpl.createComment(comment);
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
	@RequestMapping(value="/updateComment", method = RequestMethod.POST)
	public ResultModel updateComment(HttpServletRequest request, HttpServletResponse response, @RequestBody Comment comment){
		resultModel = new ResultModel();
		int result = commentServiceImpl.updateComment(comment);
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
	@RequestMapping(value="/deleteComment/{id}", method = RequestMethod.POST)
	public ResultModel deleteComment(HttpServletRequest request, HttpServletResponse response, @PathVariable int id){
		resultModel = new ResultModel();
		int result = commentServiceImpl.deleteComment((long)id);
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
	@RequestMapping(value="/getCommentListByPage", method = RequestMethod.GET)
	public ResultModel getCommentListByPage(HttpServletRequest request, HttpServletResponse response, @RequestParam int offset, @RequestParam int limit){
		resultModel = new ResultModel();
		try{
			PageModel pageModel = commentServiceImpl.getCommentListByPage(limit, offset);
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
	@RequestMapping(value="/getCommnetListByPageAndAssignmentId", method = RequestMethod.GET)
	public ResultModel getCommnetListByPageAndAssignmentId(HttpServletRequest request, HttpServletResponse response, @RequestParam int assignmentId,
			@RequestParam int offset, @RequestParam int limit){
		resultModel = new ResultModel();
		try{
			PageModel pageModel = commentServiceImpl.getCommnetListByPageAndAssignmentId(assignmentId, limit, offset);
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
