package com.jianma.zeus.service;

import com.jianma.zeus.model.Assignment;
import com.jianma.zeus.model.PageModel;

public interface AssignmentService {

	public int createAssignment(Assignment assignment);
	
    public int updateAssignment(Assignment assignment);
    
    public int deleteAssignment(Long AssignmentId);
    
    public PageModel getAssignmentListByPage(int limit, int offset);
    
    public PageModel getAssignmentListByPageAndUserId(int userId, int curriculumId, int limit, int offset);
    
    public PageModel getAssignmentListByPageAndCurriculumId(int curriculumId, int limit, int offset);
    
    public PageModel getAssignmentListByTagName(String tagName, int limit, int offset);
}
