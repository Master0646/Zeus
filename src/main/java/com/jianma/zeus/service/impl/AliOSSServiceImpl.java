package com.jianma.zeus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import com.jianma.zeus.dao.AliOSSDao;
import com.jianma.zeus.service.AliOSSService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Service
@Component
@Qualifier(value = "aliOSSServiceImpl")
public class AliOSSServiceImpl implements AliOSSService {

	@Autowired
	@Qualifier(value = "assignmentDaoImpl")
	private AliOSSDao aliOSSDaoImpl;
	
	@Override
	public int createDir(String dirName) {
		try{
			aliOSSDaoImpl.createDir(dirName);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch (OSSException oe) {
			return ResponseCodeUtil.DB_OPERATION_FAILURE;    
	    } 
		catch (ClientException ce) {
	    	return ResponseCodeUtil.DB_OPERATION_FAILURE;
	    }
	}

	@Override
	public int deleteDir(String dirName) {
		try{
			aliOSSDaoImpl.deleteDir(dirName);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch (OSSException oe) {
			return ResponseCodeUtil.DB_OPERATION_FAILURE;    
	    } 
		catch (ClientException ce) {
	    	return ResponseCodeUtil.DB_OPERATION_FAILURE;
	    }
	}

	@Override
	public int batchDeleteFile(List<String> dirNameList) {
		try{
			aliOSSDaoImpl.batchDeleteFile(dirNameList);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch (OSSException oe) {
			return ResponseCodeUtil.DB_OPERATION_FAILURE;    
	    } 
		catch (ClientException ce) {
	    	return ResponseCodeUtil.DB_OPERATION_FAILURE;
	    }
	}

}
