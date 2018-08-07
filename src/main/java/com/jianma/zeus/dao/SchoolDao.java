package com.jianma.zeus.dao;

import java.util.List;

import com.jianma.zeus.model.School;

public interface SchoolDao {

	public void createSchool(School school);
	
    public void updateSchool(School school);
    
    public void deleteSchool(Long schoolId);
    
    public List<School> getSchoolByProvince(String province);
    
    public List<School> getAcademyBySchoolName(String schoolName);
    
    public List<School> getSchoolByPage(int limit,int offset);
    
    public int countSchool();
    
    public School loadSchoolById(int id);
}
