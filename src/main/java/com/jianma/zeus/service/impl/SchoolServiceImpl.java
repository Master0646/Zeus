package com.jianma.zeus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.zeus.dao.SchoolDao;
import com.jianma.zeus.model.School;
import com.jianma.zeus.service.SchoolService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Service
@Component
@Qualifier(value = "schoolServiceImpl")
@Transactional
public class SchoolServiceImpl implements SchoolService {

	@Autowired
	@Qualifier(value = "schoolDaoImpl")
	private SchoolDao schoolDaoImpl;
	
	@Override
	public int createSchool(School school) {
		try{
			schoolDaoImpl.createSchool(school);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateSchool(School school) {
		try{
			schoolDaoImpl.updateSchool(school);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int deleteSchool(Long schoolId) {
		try{
			schoolDaoImpl.deleteSchool(schoolId);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public List<School> getSchoolByProvince(String province) {
		return schoolDaoImpl.getSchoolByProvince(province);
	}

	@Override
	public List<School> getAcademyBySchoolName(String schoolName) {
		return schoolDaoImpl.getAcademyBySchoolName(schoolName);
	}

}
