package com.jianma.zeus.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.zeus.dao.CommentDao;
import com.jianma.zeus.model.Comment;
import com.jianma.zeus.model.PageModel;
import com.jianma.zeus.service.CommentService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Service
@Component
@Qualifier(value = "commentServiceImpl")
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	@Qualifier(value = "commentDaoImpl")
	private CommentDao commentDaoImpl;
	
	@Override
	public int createComment(Comment comment) {
		try{
			comment.setCheck(true);
			commentDaoImpl.createComment(comment);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateComment(Comment comment) {
		try{
			commentDaoImpl.updateComment(comment);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int deleteComment(Long CommentId) {
		try{
			commentDaoImpl.deleteComment(CommentId);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public PageModel getCommentListByPage(int limit, int offset) {
		PageModel pageModel = new PageModel();
		pageModel.setCount(commentDaoImpl.countComment());
		pageModel.setList(commentDaoImpl.getCommentListByPage(limit, offset));
		return pageModel;
	}

	@Override
	public PageModel getCommnetListByPageAndAssignmentId(int assignmentId, int limit, int offset) {
		PageModel pageModel = new PageModel();
		pageModel.setCount(commentDaoImpl.countCommentByAssignment(assignmentId));
		pageModel.setList(commentDaoImpl.getCommnetListByPageAndAssignmentId(assignmentId, limit, offset));
		return pageModel;
	}

}
