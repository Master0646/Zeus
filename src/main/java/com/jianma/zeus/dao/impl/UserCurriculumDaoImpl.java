package com.jianma.zeus.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.zeus.dao.UserCurriculumDao;
import com.jianma.zeus.model.Comment;
import com.jianma.zeus.model.UserCurriculum;

@Repository
@Component
@Qualifier(value = "userCurriculumDaoImpl")
public class UserCurriculumDaoImpl implements UserCurriculumDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createUserCurriculum(UserCurriculum userCurriculum) {
		sessionFactory.getCurrentSession().save(userCurriculum);
	}

	@Override
	public void updateUserCurriculum(UserCurriculum userCurriculum) {
		sessionFactory.getCurrentSession().update(userCurriculum);
	}

	@Override
	public void deleteUserCurriculum(Long userCurriculumId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " update UserCurriculum uc set uc.valid = 0 where uc.id = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, userCurriculumId);
		query.executeUpdate();
	}

	@Override
	public List<UserCurriculum> getCurriculumByPage(int limit, int offset) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select c.name,c.remark,u.realname from UserCurriculum uc, Curriculum c,User u "
				+ " where uc.curriculumId = c.id and uc.valid = 1 and u.id = c.teacherId order by uc.insertAt desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List list = query.list();
		List<UserCurriculum> ucList = new ArrayList<UserCurriculum>(10);
		UserCurriculum userCurriculum = null;
        for(int i = 0; i < list.size(); i++)
        {
        	userCurriculum = new UserCurriculum();
            Object []o = (Object[])list.get(i);
          
            String name = (String)o[0];
            String remark = (String)o[1];
            String realname = (String)o[2];
            userCurriculum.setCurriculumName(name);
            userCurriculum.setCurriculumRemark(remark);
            userCurriculum.setTeacherName(realname);
            ucList.add(userCurriculum);
        }
        return ucList;
        
	}

	@Override
	public int countCurriculum() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(uc) from UserCurriculum uc ";
		Query query = session.createQuery(hql); 
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public List<UserCurriculum> getUserCurriculumByUserId(int userId, int limit, int offset) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select c.name,c.remark,u.realname from UserCurriculum uc, Curriculum c,User u "
				+ " where uc.curriculumId = c.id and uc.valid = 1 and u.id = c.teacherId order by uc.insertAt desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List list = query.list();
		List<UserCurriculum> ucList = new ArrayList<UserCurriculum>(10);
		UserCurriculum userCurriculum = null;
        for(int i = 0; i < list.size(); i++)
        {
        	userCurriculum = new UserCurriculum();
            Object []o = (Object[])list.get(i);
          
            String name = (String)o[0];
            String remark = (String)o[1];
            String realname = (String)o[2];
            userCurriculum.setCurriculumName(name);
            userCurriculum.setCurriculumRemark(remark);
            userCurriculum.setTeacherName(realname);
            ucList.add(userCurriculum);
        }
        return ucList;
	}

	@Override
	public int countUserCurriculumByUserId(int userId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(uc) from UserCurriculum uc where uc.userId = ? ";
		Query query = session.createQuery(hql); 
		query.setParameter(0, userId);
        return (int)((Long)query.uniqueResult()).longValue();
	}

}
