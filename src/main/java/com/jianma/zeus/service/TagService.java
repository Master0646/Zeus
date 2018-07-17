package com.jianma.zeus.service;

import java.util.List;

import com.jianma.zeus.model.Tag;

public interface TagService {

	public int createTag(Tag tag);
	
    public int updateTag(Tag tag);
    
    public int deleteTag(Long tagId);
    
    public List<Tag> getTagByAssignment(int assignmentId);
}
