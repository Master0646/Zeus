package com.jianma.zeus.service;

import com.jianma.zeus.model.PageModel;
import com.jianma.zeus.model.UserCurriculum;

public interface UserCurriculumService {

	public int createUserCurriculum(UserCurriculum userCurriculum);
	
    public int updateUserCurriculum(UserCurriculum userCurriculum);
    
    public int deleteUserCurriculum(Long userCurriculumId);
    
    public PageModel getCurriculumByPage(int limit, int offset);
        
    public PageModel getUserCurriculumByUserId(int userId, int limit, int offset);
    
}
