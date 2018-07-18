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
import com.jianma.zeus.model.Tag;
import com.jianma.zeus.service.TagService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Controller
@RequestMapping(value="/tag")
public class TagController extends ZeusController {

	@Autowired
	@Qualifier(value = "tagServiceImpl")
	private  TagService tagServiceImpl;
	
	@RequiresRoles(value ={""})
	@ResponseBody
	@RequestMapping(value="/createTag", method = RequestMethod.POST)
	public ResultModel createTag(HttpServletRequest request, HttpServletResponse response, @RequestBody Tag tag){
		resultModel = new ResultModel();
		int result = tagServiceImpl.createTag(tag);
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
	@RequestMapping(value="/updateTag", method = RequestMethod.POST)
	public ResultModel updateTag(HttpServletRequest request, HttpServletResponse response, @RequestBody Tag tag){
		resultModel = new ResultModel();
		int result = tagServiceImpl.updateTag(tag);
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
	@RequestMapping(value="/deleteTag/{id}", method = RequestMethod.DELETE)
	public ResultModel deleteTag(HttpServletRequest request, HttpServletResponse response,  @PathVariable int id){
		resultModel = new ResultModel();
		int result = tagServiceImpl.deleteTag((long)id);
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
	@RequestMapping(value="/getTagByAssignment", method = RequestMethod.DELETE)
	public ResultModel getTagByAssignment(HttpServletRequest request, HttpServletResponse response,  @RequestParam int assignmentId){
		resultModel = new ResultModel();
		try{
			List<Tag> list = tagServiceImpl.getTagByAssignment(assignmentId);
			resultModel.setObject(list);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		}
		catch(Exception e){
			throw new ZeusException(500, "获取数据出错");
		}
	}

}
