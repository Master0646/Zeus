package com.jianma.zeus.service;

import com.jianma.zeus.model.Comment;
import com.jianma.zeus.model.PageModel;

public interface CommentService {

	public int createComment(Comment comment);
	
    public int updateComment(Comment comment);
    
    public int deleteComment(Long CommentId);
    
    public PageModel getCommentListByPage(int limit, int offset);
    
    public PageModel getCommnetListByPageAndAssignmentId(int assignmentId, int limit, int offset);
}
