package com.jianma.zeus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.zeus.dao.TagDao;
import com.jianma.zeus.model.Tag;
import com.jianma.zeus.service.TagService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Service
@Component
@Qualifier(value = "tagServiceImpl")
@Transactional
public class TagServiceImpl implements TagService {

	@Autowired
	@Qualifier(value = "tagDaoImpl")
	private TagDao tagDaoImpl;
	
	@Override
	public int createTag(Tag tag) {
		try{
			tagDaoImpl.createTag(tag);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateTag(Tag tag) {
		try{
			tagDaoImpl.updateTag(tag);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int deleteTag(Long tagId) {
		try{
			tagDaoImpl.deleteTag(tagId);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public List<Tag> getTagByAssignment(int assignmentId) {
		
		return tagDaoImpl.getTagByAssignment(assignmentId);
	}

}
