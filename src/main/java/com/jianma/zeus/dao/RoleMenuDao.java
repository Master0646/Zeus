package com.jianma.zeus.dao;

import java.util.List;

import com.jianma.zeus.model.Menu;
import com.jianma.zeus.model.RoleMenu;

public interface RoleMenuDao {

	public void createRoleMenu(RoleMenu roleMenu);
	    
    public void deleteRoleMenu(Long roleId);
    
    public List<Menu> getMenuListByRoleId(List<Integer> roleIds);
    
    public RoleMenu loadRoleMenuById(int id);
}
