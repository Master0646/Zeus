package com.jianma.zeus.service;

import java.util.List;

import com.jianma.zeus.model.Menu;
import com.jianma.zeus.model.RoleMenu;

public interface RoleMenuService {

	public int createRoleMenu(int roleId,List<Integer> menuIdList);
    
    public int updateRoleMenu(Long roleId, List<Integer> menuIds);
    
    public List<Menu> getMenuListByRoleId(List<Integer> roleIds);
    
    public RoleMenu loadRoleMenuById(int id);
    
    public int deleteRoleMenuByRoleMenuId(Long id);
}
