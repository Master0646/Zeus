package com.jianma.zeus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.zeus.dao.AssignmentDao;
import com.jianma.zeus.model.Assignment;
import com.jianma.zeus.model.PageModel;
import com.jianma.zeus.service.AssignmentService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Service
@Component
@Qualifier(value = "assignmentServiceImpl")
@Transactional
public class AssignmentServiceImpl implements AssignmentService {

	@Autowired
	@Qualifier(value = "assignmentDaoImpl")
	private AssignmentDao assignmentDaoImpl;
	
	@Override
	public int createAssignment(Assignment assignment) {
		try{
			assignmentDaoImpl.createAssignment(assignment);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateAssignment(Assignment assignment) {
		try{
			assignmentDaoImpl.updateAssignment(assignment);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int deleteAssignment(Long AssignmentId) {
		try{
			assignmentDaoImpl.deleteAssignment(AssignmentId);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public PageModel getAssignmentListByPage(int limit, int offset) {
		PageModel pageModel = new PageModel();
		pageModel.setCount(assignmentDaoImpl.countAssignment());
		pageModel.setList(assignmentDaoImpl.getAssignmentListByPage(limit, offset));
		return pageModel;
	}

	@Override
	public PageModel getAssignmentListByPageAndUserId(int userId, int curriculumId, int limit, int offset) {
		PageModel pageModel = new PageModel();
		pageModel.setCount(assignmentDaoImpl.countAssignmentByUserIdAndCurriculumId(userId, curriculumId));
		pageModel.setList(assignmentDaoImpl.getAssignmentListByPageAndUserId(userId, curriculumId, limit, offset));
		return pageModel;
	}

	@Override
	public PageModel getAssignmentListByPageAndCurriculumId(int curriculumId, int limit, int offset) {
		PageModel pageModel = new PageModel();
		pageModel.setCount(assignmentDaoImpl.countAssignmentByCurriculumId(curriculumId));
		pageModel.setList(assignmentDaoImpl.getAssignmentListByPageAndCurriculumId(curriculumId, limit, offset));
		return pageModel;
	}

	@Override
	public PageModel getAssignmentListByTagName(String tagName, int limit, int offset) {
		PageModel pageModel = new PageModel();
		pageModel.setCount(assignmentDaoImpl.countAssignmentByTagName(tagName));
		pageModel.setList(assignmentDaoImpl.getAssignmentListByTagName(tagName, limit, offset));
		return pageModel;
	}

	@Override
	public Assignment loadAssignmentById(int id) {
		return assignmentDaoImpl.loadAssignmentById(id);
	}

}
