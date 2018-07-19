package com.jianma.zeus.dao.impl;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.ObjectMetadata;
import com.jianma.zeus.dao.AliOSSDao;
import com.jianma.zeus.util.ConfigInfo;

@Repository
@Component
@Qualifier(value = "aliOSSDaoImpl")
public class AliOSSDaoImpl implements AliOSSDao {

	@Autowired
	@Qualifier(value = "configInfo")
	private ConfigInfo configInfo;
	
	/**
	 * 创建文件夹
	 */
	@Override
	public void createDir(String dirName) {
		String endpoint = configInfo.endpoint;
        String accessId = configInfo.accessId;
        String accessKey = configInfo.accessKey;
        String bucket = configInfo.bucket;
        
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        
        String objectName = dirName+"/";
        
        boolean found = client.doesObjectExist(bucket, objectName);
        if(!found){
        	ObjectMetadata objectMeta = new ObjectMetadata();
    		
    		byte[] buffer = new byte[0];
    		ByteArrayInputStream in = new ByteArrayInputStream(buffer);
    		objectMeta.setContentLength(0);
    		try {
    			client.putObject(bucket, objectName, in, objectMeta);
    		}
    		finally {
    			client.shutdown();
    			try {
    				in.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
        }
	}

	/**
	 * 删除文件夹
	 */
	@Override
	public void deleteDir(String dirName) {
		String endpoint = configInfo.endpoint;
        String accessId = configInfo.accessId;
        String accessKey = configInfo.accessKey;
        String bucket = configInfo.bucket;
        
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        
        String objectName = dirName+"/";
        
        boolean found = client.doesObjectExist(bucket, objectName);
        if(found){
        	client.deleteObject(bucket, objectName);
        }
        
        client.shutdown();
	}

	/**
	 * 删除文件对象
	 */
	@Override
	public void batchDeleteFile(List<String> dirNameList) {
		String endpoint = configInfo.endpoint;
        String accessId = configInfo.accessId;
        String accessKey = configInfo.accessKey;
        String bucket = configInfo.bucket;
        
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
              
        try {
                        
            DeleteObjectsResult deleteObjectsResult = client.deleteObjects(
                    new DeleteObjectsRequest(bucket).withKeys(dirNameList));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
            for (String object : deletedObjects) {
                System.out.println("\t" + object);
            }
    		
        } finally {
            client.shutdown();
        }
        
	}

}
