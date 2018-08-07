package com.jianma.zeus.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.zeus.dao.AssignmentDao;
import com.jianma.zeus.model.Assignment;

@Repository
@Component
@Qualifier(value = "assignmentDaoImpl")
public class AssignmentDaoImpl implements AssignmentDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createAssignment(Assignment assignment) {
		sessionFactory.getCurrentSession().save(assignment);
	}

	@Override
	public void updateAssignment(Assignment assignment) {
		sessionFactory.getCurrentSession().update(assignment);
	}

	@Override
	public void deleteAssignment(Long AssignmentId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " update Assignment a set a.valid = 0 where a.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, AssignmentId.intValue());
		query.executeUpdate();
	}

	@Override
	public List<Assignment> getAssignmentListByPage(int limit, int offset) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Assignment a order by updateAt desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public int countAssignment() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(a) from Assignment a ";
		Query query = session.createQuery(hql); 
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public List<Assignment> getAssignmentListByPageAndUserId(int userId, int curriculumId, int limit, int offset) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Assignment a where a.userId = ? order by updateAt desc";
		
		if (curriculumId > 0){
			hql = "from Assignment a where a.userId = ? and a.curriculumId = ? order by updateAt desc";
		}
		
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		if (curriculumId > 0){
			query.setParameter(1, curriculumId);
		}
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public int countAssignmentByUserIdAndCurriculumId(int userId, int curriculumId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(a) from Assignment a where a.userId = ? ";
		if (curriculumId > 0){
			hql = "select count(a) from Assignment a where a.userId = ? and a.curriculumId = ? ";
		}
		
		Query query = session.createQuery(hql);
		query.setParameter(0, userId);
		if (curriculumId > 0){
			query.setParameter(1, curriculumId);
		}
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public List<Assignment> getAssignmentListByPageAndCurriculumId(int curriculumId, int limit, int offset) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "from Assignment a where  a.curriculumId = ? order by updateAt desc";
	
		Query query = session.createQuery(hql);
		query.setParameter(0, curriculumId);
		
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public int countAssignmentByCurriculumId(int curriculumId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(a) from Assignment a where a.curriculumId = ? ";
		
		Query query = session.createQuery(hql);
		query.setParameter(0, curriculumId);
		
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public List<Assignment> getAssignmentListByTagName(String tagName, int limit, int offset) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select a.* from Assignment a, Tag t where a.id = t.assignmentId and  t.tagName = ? order by a.updateAt desc";
		
		Query query = session.createQuery(hql);
		
		query.setParameter(0, tagName);
		
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List list = query.list();
		
		
        return list;
	}

	@Override
	public int countAssignmentByTagName(String tagName) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(a) from Assignment a,Tag t where a.id = t.assignmentId and t.tagName = ? ";
		
		Query query = session.createQuery(hql);
		query.setParameter(0, tagName);
		
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public Assignment loadAssignmentById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Assignment)session.get(Assignment.class, id);
	}

}
