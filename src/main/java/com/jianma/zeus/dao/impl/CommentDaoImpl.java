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

import com.jianma.zeus.dao.CommentDao;
import com.jianma.zeus.model.Comment;

@Repository
@Component
@Qualifier(value = "commentDaoImpl")
public class CommentDaoImpl implements CommentDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createComment(Comment comment) {
		sessionFactory.getCurrentSession().save(comment);
	}

	@Override
	public void updateComment(Comment comment) {
		sessionFactory.getCurrentSession().update(comment);
	}

	@Override
	public void deleteComment(Long CommentId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " update Comment c set c.check = 0 where c.id = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, CommentId.intValue());
		query.executeUpdate();
	}

	@Override
	public List<Comment> getCommentListByPage(int limit, int offset) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Comment c order by createAt desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		return query.list();
	}

	@Override
	public int countComment() {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(c) from Comment c ";
		Query query = session.createQuery(hql); 
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public List<Comment> getCommnetListByPageAndAssignmentId(int assignmentId, int limit, int offset) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select c.content,u.nickname,u.headPortrait from Comment c, User u "
				+ " where c.check = 1 and c.userId = u. id and c.assignmentId = ? order by c.createAt desc";
		
		Query query = session.createQuery(hql);
		query.setParameter(0, assignmentId);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List list = query.list();
		
		List<Comment> cList = new ArrayList<Comment>(10);
		Comment comment = null;
        for(int i = 0; i < list.size(); i++)
        {
        	comment = new Comment();
            Object []o = (Object[])list.get(i);
          
            String content = (String)o[0];
            String nickName = (String)o[1];
            String headPortrait = (String)o[2];
            comment.setNickName(nickName);            
            comment.setContent(content);
            comment.setHeadPortrait(headPortrait);
            cList.add(comment);
        }
        return cList;
	}

	@Override
	public int countCommentByAssignment(int assignmentId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "select count(c) from Comment c where c.assignmentId = ? ";
		Query query = session.createQuery(hql); 
		query.setParameter(0, assignmentId);
        return (int)((Long)query.uniqueResult()).longValue();
	}

}
