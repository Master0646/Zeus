package com.jianma.zeus.dao;

import java.util.List;

import com.jianma.zeus.model.Comment;

public interface CommentDao {

	public void createComment(Comment comment);
	
    public void updateComment(Comment comment);
    
    public void deleteComment(Long CommentId);
    
    public List<Comment> getCommentListByPage(int limit, int offset);
    
    public int countComment();
    
    public List<Comment> getCommnetListByPageAndAssignmentId(int assignmentId, int limit, int offset);
    
    public int countCommentByAssignment(int assignmentId);
}
