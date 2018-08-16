package com.jianma.zeus.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.zeus.dao.SchoolDao;
import com.jianma.zeus.model.PageModel;
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
			school.setCode(UUID.randomUUID().toString().replace("-", "").toLowerCase());
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
			schoolDaoImpl.deleteSchoolByParentId(schoolId.intValue());
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public List<School> getSchoolByProvince(String province) {
		return schoolDaoImpl.getSchoolByProvince(province);
	}

	@Override
	public List<School> getAcademyBySchoolId(int schoolId) {
		return schoolDaoImpl.getAcademyBySchoolId(schoolId);
	}

	@Override
	public PageModel getSchoolByPage(int limit, int offset) {
		PageModel pModel = new PageModel();
		pModel.setCount(schoolDaoImpl.countSchool());
		pModel.setList(schoolDaoImpl.getSchoolByPage(limit, offset));
		return pModel;
	}

	@Override
	public School loadSchoolById(int id) {
		return schoolDaoImpl.loadSchoolById(id);
	}

	@Override
	public List<School> getAllSchool() {
		return schoolDaoImpl.getAllSchool();
	}

}
