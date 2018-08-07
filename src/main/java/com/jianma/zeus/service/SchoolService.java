package com.jianma.zeus.service;

import java.util.List;

import com.jianma.zeus.model.PageModel;
import com.jianma.zeus.model.School;

public interface SchoolService {

	public int createSchool(School school);
	
    public int updateSchool(School school);
    
    public int deleteSchool(Long schoolId);
    
    public List<School> getSchoolByProvince(String province);
    
    public List<School> getAcademyBySchoolName(String schoolName);
    
    public PageModel getSchoolByPage(int limit,int offset);
    
    public School loadSchoolById(int id);
}
