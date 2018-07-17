package com.jianma.zeus.dao;

import java.util.List;

import com.jianma.zeus.model.Assignment;

public interface AssignmentDao {

	public void createAssignment(Assignment assignment);
	
    public void updateAssignment(Assignment assignment);
    
    public void deleteAssignment(Long AssignmentId);
    
    public List<Assignment> getAssignmentListByPage(int limit, int offset);
    
    public int countAssignment();
    
    public List<Assignment> getAssignmentListByPageAndUserId(int userId, int curriculumId, int limit, int offset);
    
    public int countAssignmentByUserIdAndCurriculumId(int userId, int curriculumId);
    
    public List<Assignment> getAssignmentListByPageAndCurriculumId(int curriculumId, int limit, int offset);
    
    public int countAssignmentByCurriculumId(int curriculumId);
    
    public List<Assignment> getAssignmentListByTagName(String tagName, int limit, int offset);
    
    public int countAssignmentByTagName(String tagName);
}
