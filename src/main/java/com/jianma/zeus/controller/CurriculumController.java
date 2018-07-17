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
import com.jianma.zeus.model.Curriculum;
import com.jianma.zeus.model.ResultModel;
import com.jianma.zeus.service.CurriculumService;

@Controller
@RequestMapping(value = "/curriculum")
public class CurriculumController extends ZeusController {

	@Autowired
	@Qualifier(value = "curriculumServiceImpl")
	private CurriculumService curriculumServiceImpl;

	@RequiresRoles(value = { "" })
	@ResponseBody
	@RequestMapping(value = "/createCurriculum", method = RequestMethod.POST)
	public ResultModel createCurriculum(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Curriculum curriculum) {
		resultModel = new ResultModel();
		try {

			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new ZeusException(500, "创建出错");
		}
	}

	@RequiresRoles(value = { "" })
	@ResponseBody
	@RequestMapping(value = "/updateCurriculum", method = RequestMethod.POST)
	public ResultModel updateCurriculum(HttpServletRequest request, HttpServletResponse response,
			@RequestBody Curriculum curriculum) {
		resultModel = new ResultModel();
		try {

			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new ZeusException(500, "创建出错");
		}
	}

	@RequiresRoles(value = { "" })
	@ResponseBody
	@RequestMapping(value = "/deleteCurriculum/{id}", method = RequestMethod.GET)
	public ResultModel deleteCurriculum(HttpServletRequest request, HttpServletResponse response,
			@PathVariable int id) {
		resultModel = new ResultModel();
		try {

			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new ZeusException(500, "创建出错");
		}
	}

	@RequiresRoles(value = { "" })
	@ResponseBody
	@RequestMapping(value = "/getCurriculumListByPage", method = RequestMethod.GET)
	public ResultModel getCurriculumListByPage(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int offset, @RequestParam int limit) {
		resultModel = new ResultModel();
		try {

			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new ZeusException(500, "创建出错");
		}
	}

	@RequiresRoles(value = { "" })
	@ResponseBody
	@RequestMapping(value = "/getCurriculumListByPage", method = RequestMethod.GET)
	public ResultModel getCurriculumListByPageAndTeacher(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int teacherId, @RequestParam int offset, @RequestParam int limit) {
		resultModel = new ResultModel();
		try {

			resultModel.setResultCode(200);
			resultModel.setSuccess(true);
			return resultModel;
		} catch (Exception e) {
			throw new ZeusException(500, "创建出错");
		}
	}

}
