package com.jianma.zeus.dao;

import java.util.List;

public interface AliOSSDao {
	
	/**
	 * 创建文件夹
	 * @param dirName 文件夹名
	 */
	public void createDir(String dirName);
	
	/**
	 * 删除文件夹
	 * @param dirName 文件夹名
	 */
	public void deleteDir(String dirName);
	
	/**
	 * 批量删除文件对象
	 * @param dirNameList  
	 */
	public void batchDeleteFile(List<String> dirNameList);
}
