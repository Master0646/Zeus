package com.jianma.zeus.dao;

import java.util.List;

import com.jianma.zeus.model.Curriculum;

public interface CurriculumDao {

	public void createCurriculum(Curriculum curriculum);
	
    public void updateCurriculum(Curriculum curriculum);
    
    public void deleteCurriculum(Long curriculumId);
    
    public List<Curriculum> getCurriculumListByPage(int limit, int offset);
    
    public int countCurriculum();
    
    public List<Curriculum> getCurriculumListByPageAndTeacher(int teacherId, int limit, int offset);
    
    public int countCurriculumByTeacher(int teacherId);
    
    public Curriculum loadCurriculumById(int id);
}
