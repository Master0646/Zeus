package com.jianma.zeus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.zeus.dao.UserCurriculumDao;
import com.jianma.zeus.model.PageModel;
import com.jianma.zeus.model.UserCurriculum;
import com.jianma.zeus.service.UserCurriculumService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Service
@Component
@Qualifier(value = "userCurriculumServiceImpl")
@Transactional
public class UserCurriculumServiceImpl implements UserCurriculumService {

	@Autowired
	@Qualifier(value = "userCurriculumDaoImpl")
	private UserCurriculumDao userCurriculumDaoImpl;
	
	@Override
	public int createUserCurriculum(UserCurriculum userCurriculum) {
		try{
			userCurriculumDaoImpl.createUserCurriculum(userCurriculum);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateUserCurriculum(UserCurriculum userCurriculum) {
		try{
			userCurriculumDaoImpl.updateUserCurriculum(userCurriculum);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int deleteUserCurriculum(Long userCurriculumId) {
		try{
			userCurriculumDaoImpl.deleteUserCurriculum(userCurriculumId);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public PageModel getCurriculumByPage(int limit, int offset) {
		PageModel pageModel = new PageModel();
		pageModel.setCount(userCurriculumDaoImpl.countCurriculum());
		pageModel.setList(userCurriculumDaoImpl.getCurriculumByPage(limit, offset));
		return pageModel;
	}

	@Override
	public PageModel getUserCurriculumByUserId(int userId, int limit, int offset) {
		PageModel pageModel = new PageModel();
		pageModel.setCount(userCurriculumDaoImpl.countUserCurriculumByUserId(userId));
		pageModel.setList(userCurriculumDaoImpl.getCurriculumByPage(limit, offset));
		return pageModel;
	}

}
