package com.jianma.zeus.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.zeus.dao.RoleMenuDao;
import com.jianma.zeus.model.Menu;
import com.jianma.zeus.model.RoleMenu;
import com.jianma.zeus.service.RoleMenuService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Service
@Component
@Qualifier(value = "roleMenuServiceImpl")
@Transactional
public class RoleMenuServiceImpl implements RoleMenuService {

	@Autowired
	@Qualifier(value = "roleMenuDaoImpl")
	private RoleMenuDao roleMenuDaoImpl;
	
	@Override
	public int createRoleMenu(int roleId,List<Integer> menuIdList) {
		try{
			RoleMenu roleMenu = null;
			for (Integer menuId : menuIdList){
				roleMenu = new RoleMenu();
				roleMenu.setMenuId(menuId);
				roleMenu.setRoleId(roleId);
				roleMenuDaoImpl.createRoleMenu(roleMenu);
			}
			
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			e.printStackTrace();
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateRoleMenu(Long roleId, List<Integer> menuIds) {
		try{
			roleMenuDaoImpl.deleteRoleMenuByRoleId(roleId);
			RoleMenu roleMenu = null;
			for (Integer menuId : menuIds){
				roleMenu = new RoleMenu();
				roleMenu.setRoleId(roleId.intValue());
				roleMenu.setMenuId(menuId);
				roleMenuDaoImpl.createRoleMenu(roleMenu);
			}
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public List<Menu> getMenuListByRoleId(List<Integer> roleIds) {
		List<Menu> list = roleMenuDaoImpl.getMenuListByRoleId(roleIds);
		Set<Menu> set = new HashSet<Menu>(list);  
		list.clear();   
	    list.addAll(set);
		return list;
	}

	@Override
	public RoleMenu loadRoleMenuById(int id) {
		
		return roleMenuDaoImpl.loadRoleMenuById(id);
	}

	@Override
	public int deleteRoleMenuByRoleMenuId(Long id) {
		try{
			roleMenuDaoImpl.deleteRoleMenuByRoleMenuId(id);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

}
