package com.jianma.zeus.dao;

import java.util.List;

import com.jianma.zeus.model.UserCurriculum;

public interface UserCurriculumDao {

	public void createUserCurriculum(UserCurriculum userCurriculum);
	
    public void updateUserCurriculum(UserCurriculum userCurriculum);
    
    public void deleteUserCurriculum(Long userCurriculumId);
    
    public List<UserCurriculum> getCurriculumByPage(int limit, int offset);
    
    public int countCurriculum();
    
    public List<UserCurriculum> getUserCurriculumByUserId(int userId, int limit, int offset);
    
    public int countUserCurriculumByUserId(int userId);
}
