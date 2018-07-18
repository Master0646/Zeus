package com.jianma.zeus.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.zeus.dao.SchoolDao;
import com.jianma.zeus.model.School;

@Repository
@Component
@Qualifier(value = "schoolDaoImpl")
public class SchoolDaoImpl implements SchoolDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createSchool(School school) {
		sessionFactory.getCurrentSession().save(school);
	}

	@Override
	public void updateSchool(School school) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " update from School s set s.name = ?, s.province = ?, s.academy = ? where s.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, school.getName());
		query.setParameter(1, school.getProvince());
		query.setParameter(2, school.getAcademy());
		query.setParameter(3, school.getId());
		query.executeUpdate();
	}

	@Override
	public void deleteSchool(Long schoolId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from School s  where s.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, schoolId);
		query.executeUpdate();
	}

	@Override
	public List<School> getSchoolByProvince(String province) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from School s where s.province = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, province);
		return query.list();
	}

	@Override
	public List<School> getAcademyBySchoolName(String schoolName) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from School s where s.name = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, schoolName);
		return query.list();
	}

}
