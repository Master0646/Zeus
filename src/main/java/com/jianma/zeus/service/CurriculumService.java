package com.jianma.zeus.service;

import com.jianma.zeus.model.Curriculum;
import com.jianma.zeus.model.PageModel;

public interface CurriculumService {

	public int createCurriculum(Curriculum curriculum);
	
    public int updateCurriculum(Curriculum curriculum);
    
    public int deleteCurriculum(Long curriculumId);
    
    public PageModel getCurriculumListByPage(int limit, int offset);
    
    public PageModel getCurriculumListByPageAndTeacher(int teacherId, int limit, int offset);
    
}
