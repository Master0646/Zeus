package com.jianma.zeus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.zeus.dao.CurriculumDao;
import com.jianma.zeus.model.Curriculum;
import com.jianma.zeus.model.PageModel;
import com.jianma.zeus.service.CurriculumService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Service
@Component
@Qualifier(value = "curriculumServiceImpl")
@Transactional
public class CurriculumServiceImpl implements CurriculumService {

	@Autowired
	@Qualifier(value = "curriculumDaoImpl")
	private CurriculumDao curriculumDaoImpl;
	
	@Override
	public int createCurriculum(Curriculum curriculum) {
		try{
			curriculumDaoImpl.createCurriculum(curriculum);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateCurriculum(Curriculum curriculum) {
		try{
			curriculumDaoImpl.updateCurriculum(curriculum);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int deleteCurriculum(Long curriculumId) {
		try{
			curriculumDaoImpl.deleteCurriculum(curriculumId);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public PageModel getCurriculumListByPage(int limit, int offset) {
		PageModel pageModel = new PageModel();
		pageModel.setCount(curriculumDaoImpl.countCurriculum());
		pageModel.setList(curriculumDaoImpl.getCurriculumListByPage(limit, offset));
		return pageModel;
	}

	@Override
	public PageModel getCurriculumListByPageAndTeacher(int teacherId, int limit, int offset) {
		PageModel pageModel = new PageModel();
		pageModel.setCount(curriculumDaoImpl.countCurriculumByTeacher(teacherId));
		pageModel.setList(curriculumDaoImpl.getCurriculumListByPageAndTeacher(teacherId, limit, offset));
		return pageModel;
	}

	@Override
	public Curriculum loadCurriculumById(int id) {
		return curriculumDaoImpl.loadCurriculumById(id);
	}

}
