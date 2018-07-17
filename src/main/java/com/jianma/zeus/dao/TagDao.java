package com.jianma.zeus.dao;

import java.util.List;

import com.jianma.zeus.model.Tag;

public interface TagDao {
	
	public void createTag(Tag tag);
	
    public void updateTag(Tag tag);
    
    public void deleteTag(Long tagId);
    
    public List<Tag> getTagByAssignment(int assignmentId);
}
