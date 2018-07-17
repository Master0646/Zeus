package com.jianma.zeus.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.zeus.dao.TagDao;
import com.jianma.zeus.model.Tag;

@Repository
@Component
@Qualifier(value = "tagDaoImpl")
public class TagDaoImpl implements TagDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createTag(Tag tag) {
		sessionFactory.getCurrentSession().save(tag);
	}

	@Override
	public void updateTag(Tag tag) {
		sessionFactory.getCurrentSession().update(tag);
	}

	@Override
	public void deleteTag(Long tagId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete from Tag t  where t.id = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, tagId);
		query.executeUpdate();
	}

	@Override
	public List<Tag> getTagByAssignment(int assignmentId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Tag t where t.assignmentId = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, assignmentId);
		return query.list();
	}

}
