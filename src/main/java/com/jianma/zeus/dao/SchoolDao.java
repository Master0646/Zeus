package com.jianma.zeus.dao;

import java.util.List;
import java.util.Map;

import com.jianma.zeus.model.School;

public interface SchoolDao {

	public void createSchool(School school);
	
    public void updateSchool(School school);
    
    public void deleteSchool(Long schoolId);
    
    public void deleteSchoolByParentId(int parentId);
    
    public List<School> getSchoolByProvince(String province);
    
    public List<School> getAcademyBySchoolId(int schoolId);
    
    public List<School> getAllSchool();
    
    public List<School> getSchoolByPage(int limit,int offset);
    
    public int countSchool();
    
    public School loadSchoolById(int id);
    
    public Map<Integer,String> getMapOfAllSchool();
}
