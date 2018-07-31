package com.jianma.zeus.dao;

import java.util.List;

import com.jianma.zeus.model.Menu;

public interface MenuDao {

	public void createMenu(Menu menu);
	
    public void updateMenu(Menu menu);
    
    public void deleteMenu(Long menuId);
    
    public List<Menu> getMenuList();
}
