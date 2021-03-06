package com.jianma.zeus.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		String hql = " update from School s set s.name = ?, s.province = ? where s.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, school.getName());
		query.setParameter(1, school.getProvince());
		query.setParameter(2, school.getId());
		query.executeUpdate();
	}

	@Override
	public void deleteSchool(Long schoolId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from School s  where s.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, schoolId.intValue());
		query.executeUpdate();
	}

	@Override
	public List<School> getSchoolByProvince(String province) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from School s where s.province = ? and parentId = 0";
		Query query = session.createQuery(hql);
		query.setParameter(0, province);
		return query.list();
	}

	@Override
	public List<School> getAcademyBySchoolId(int schoolId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from School s where s.parentId = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, schoolId);
		return query.list();
	}

	@Override
	public List<School> getSchoolByPage(int limit, int offset) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from School s order by createAt desc ";
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public int countSchool() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(s) from School s ";
		Query query = session.createQuery(hql); 
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public School loadSchoolById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (School)session.get(School.class, id);
	}

	@Override
	public List<School> getAllSchool() {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from School s where parentId = 0 order by createAt desc ";
		Query query = session.createQuery(hql);
		return query.list();
	}

	@Override
	public void deleteSchoolByParentId(int parentId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from School s  where s.parentId = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, parentId);
		query.executeUpdate();
	}

	@Override
	public Map<Integer, String> getMapOfAllSchool() {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select id,name from School s order by createAt desc ";
		Query query = session.createQuery(hql);
		List list = query.list();
		Map<Integer,String> schoolMap = new HashMap<>();
		for(int i = 0; i < list.size(); i++)
        {
			Object []o = (Object[])list.get(i);
            
            int id = ((Number)o[0]).intValue();
			String name = (String)o[1];
			schoolMap.put(id, name);
        }
		return schoolMap;
	}

}
