package com.jianma.zeus.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PolicyConditions;
import com.jianma.zeus.ZeusController;
import com.jianma.zeus.model.PageModel;
import com.jianma.zeus.model.ResultModel;
import com.jianma.zeus.util.ConfigInfo;


@Controller
@RequestMapping(value="/alioss")
public class AliOSSController extends ZeusController {

	@Autowired
	@Qualifier(value = "configInfo")
	private ConfigInfo configInfo;
	
	@RequestMapping(value = "/uploadKey/{type}", method = RequestMethod.GET)
	public @ResponseBody  Map<String, String> uploadKey(HttpServletRequest request,HttpServletResponse response,Locale locale, Model model, @PathVariable int type) {
		
		Subject subject = SecurityUtils.getSubject();
		Object schoolCode = subject.getSession().getAttribute("schoolCode");
		
		String endpoint = configInfo.endpoint;
        String accessId = configInfo.accessId;
        String accessKey = configInfo.accessKey;
        String bucket = configInfo.bucket;
        String dir = schoolCode + "/";
                
        String host = "http://" + bucket + "." + endpoint;
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
        try { 	
        	long expireTime = 30;
        	long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, dir);

            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);
            
            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            //respMap.put("expire", formatISO8601Date(expiration));
            respMap.put("dir", dir);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            return respMap;
            
        } catch (Exception e) {
            
            return null;
        }
        
	}
	
	@RequestMapping(value = "/createDir", method = RequestMethod.POST)
	public @ResponseBody  ResultModel createDir(HttpServletRequest request,HttpServletResponse response,
			Locale locale, Model model,  @RequestParam String dirName) {
		resultModel = new ResultModel();
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
    			resultModel.setSuccess(true);
    			resultModel.setResultCode(200);
    		} 
    		catch(Exception e){
    			e.printStackTrace();
    			resultModel.setSuccess(false);
    			resultModel.setResultCode(500);
    		}
    		finally {
    			try {
    				in.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
        }
        
        // 关闭OSSClient。
        client.shutdown();
        
		return resultModel;
	}
	
	@RequestMapping(value = "/deleteDir", method = RequestMethod.POST)
	public @ResponseBody  ResultModel deleteDir(HttpServletRequest request,HttpServletResponse response,
			Locale locale, Model model,  @RequestParam String dirName) {
		resultModel = new ResultModel();
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
        
        // 关闭OSSClient。
        client.shutdown();
        
        resultModel.setSuccess(true);
		resultModel.setResultCode(200);
		return resultModel;
	}
	
	@RequestMapping(value = "/batchDeleteFile", method = RequestMethod.POST)
	public @ResponseBody  ResultModel batchDeleteFile(HttpServletRequest request,HttpServletResponse response,
			Locale locale, Model model,  @RequestParam String dirNames) {
		resultModel = new ResultModel();
		String endpoint = configInfo.endpoint;
        String accessId = configInfo.accessId;
        String accessKey = configInfo.accessKey;
        String bucket = configInfo.bucket;
        
        OSSClient client = new OSSClient(endpoint, accessId, accessKey);
              
        try {
            
        	List<String> keys = Arrays.stream(dirNames.split(",")).collect(Collectors.toList());
        	
            
            DeleteObjectsResult deleteObjectsResult = client.deleteObjects(
                    new DeleteObjectsRequest(bucket).withKeys(keys));
            List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
            for (String object : deletedObjects) {
                System.out.println("\t" + object);
            }
            resultModel.setSuccess(true);
    		resultModel.setResultCode(200);
    		
        } catch (OSSException oe) {
            
            resultModel.setSuccess(false);
    		resultModel.setResultCode(500);
        } catch (ClientException ce) {
        	resultModel.setSuccess(false);
     		resultModel.setResultCode(500);
        } finally {
           
            client.shutdown();
        }
        
       
		return resultModel;
	}
}
