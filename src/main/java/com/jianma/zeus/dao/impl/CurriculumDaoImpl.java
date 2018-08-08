package com.jianma.zeus.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.zeus.dao.CurriculumDao;
import com.jianma.zeus.model.Curriculum;

@Repository
@Component
@Qualifier(value = "curriculumDaoImpl")
public class CurriculumDaoImpl implements CurriculumDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createCurriculum(Curriculum curriculum) {
		sessionFactory.getCurrentSession().save(curriculum);
	}

	@Override
	public void updateCurriculum(Curriculum curriculum) {
		sessionFactory.getCurrentSession().update(curriculum);
	}

	@Override
	public void deleteCurriculum(Long curriculumId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " update Curriculum c set c.valid = 0 where c.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, curriculumId.intValue());
		query.executeUpdate();
	}

	@Override
	public List<Curriculum> getCurriculumListByPage(int limit, int offset) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Curriculum c where c.valid = 1 order by updateAt desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public int countCurriculum() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(c) from Curriculum c where c.valid = 1 ";
		Query query = session.createQuery(hql); 
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public List<Curriculum> getCurriculumListByPageAndTeacher(int teacherId, int limit, int offset) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Curriculum c where c.teacherId = ? and c.valid = 1 order by updateAt desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		query.setParameter(0, teacherId);
		return query.list();
	}

	@Override
	public int countCurriculumByTeacher(int teacherId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(c) from Curriculum c where c.teacherId = ? and c.valid = 1 ";
		Query query = session.createQuery(hql); 
		query.setParameter(0, teacherId);
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public Curriculum loadCurriculumById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (Curriculum)session.get(Curriculum.class, id);
	}

}
