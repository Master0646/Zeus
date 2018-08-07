package com.jianma.zeus.controller;

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
import com.jianma.zeus.model.Curriculum;
import com.jianma.zeus.model.Menu;
import com.jianma.zeus.model.PageModel;
import com.jianma.zeus.model.ResultModel;
import com.jianma.zeus.service.CurriculumService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Controller
@RequestMapping(value = "/curriculum")
public class CurriculumController extends ZeusController {

	@Autowired
	@Qualifier(value = "curriculumServiceImpl")
	private CurriculumService curriculumServiceImpl;
		
	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/curriculumManage")
	public String curriculumManage(HttpServletRequest request, Model model) {
		return "admin/curriculumManage";
	}

	@RequiresRoles(value = { "管理员" })
	@RequestMapping(value = "/alterCurriculum/{id}")
	public ModelAndView alterCurriculum(HttpServletRequest request, Model model,@PathVariable int id) {
		ModelAndView modelView = new ModelAndView();
		if (id > 0){
			Curriculum curriculum = curriculumServiceImpl.loadCurriculumById(id);
			modelView.addObject("curriculum", curriculum);
		}
		modelView.setViewName("admin/alterCurriculum");
		return modelView;
	}
	
	@RequiresRoles(value = { "" })
	@ResponseBody
	@RequestMapping(value = "/createCurriculum", method = RequestMethod.POST)
	public ResultModel createCurriculum(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Curriculum curriculum) {
		resultModel = new ResultModel();
		int result = curriculumServiceImpl.createCurriculum(curriculum);
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
	@RequestMapping(value = "/updateCurriculum", method = RequestMethod.POST)
	public ResultModel updateCurriculum(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Curriculum curriculum) {
		resultModel = new ResultModel();
		int result = curriculumServiceImpl.updateCurriculum(curriculum);
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
	@RequestMapping(value = "/deleteCurriculum/{id}", method = RequestMethod.DELETE)
	public ResultModel deleteCurriculum(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int id) {
		resultModel = new ResultModel();
		int result = curriculumServiceImpl.deleteCurriculum((long)id);
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
	@RequestMapping(value = "/getCurriculumListByPage", method = RequestMethod.GET)
	public ResultModel getCurriculumListByPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int offset, @RequestParam int limit) {
		resultModel = new ResultModel();
		try {
			PageModel pageModel = curriculumServiceImpl.getCurriculumListByPage(limit, offset);
			resultModel.setObject(pageModel);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new ZeusException(500, "获取数据出错");
		}
	}

	@RequiresRoles(value = { "" })
	@ResponseBody
	@RequestMapping(value = "/getCurriculumListByPageAndTeacher", method = RequestMethod.GET)
	public ResultModel getCurriculumListByPageAndTeacher(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int teacherId, @RequestParam int offset, @RequestParam int limit) {
		resultModel = new ResultModel();
		try {
			PageModel pageModel = curriculumServiceImpl.getCurriculumListByPageAndTeacher(teacherId, limit, offset);
			resultModel.setObject(pageModel);
			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new ZeusException(500, "获取数据出错");
		}
	}

}
