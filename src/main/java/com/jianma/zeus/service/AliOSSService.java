package com.jianma.zeus.service;

import java.util.List;

public interface AliOSSService {
	
	/**
	 * 创建文件夹
	 * @param dirName 文件夹名
	 */
	public int createDir(String dirName);
	
	/**
	 * 删除文件夹
	 * @param dirName 文件夹名
	 */
	public int deleteDir(String dirName);
	
	/**
	 * 批量删除文件对象
	 * @param dirNameList  
	 */
	public int batchDeleteFile(List<String> dirNameList);
}
