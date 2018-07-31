package com.jianma.zeus.service;

import java.util.List;

import com.jianma.zeus.model.Menu;

public interface MenuService {

	public int createMenu(Menu menu);
	
    public int updateMenu(Menu menu);
    
    public int deleteMenu(Long menuId);
    
    public List<Menu> getMenuList();
}
