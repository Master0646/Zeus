package com.jianma.zeus.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.zeus.dao.MenuDao;
import com.jianma.zeus.model.Menu;
import com.jianma.zeus.service.MenuService;
import com.jianma.zeus.util.ResponseCodeUtil;

@Service
@Component
@Qualifier(value = "menuServiceImpl")
@Transactional
public class MenuServiceImpl implements MenuService {

	@Autowired
	@Qualifier(value = "menuDaoImpl")
	private MenuDao menuDaoImpl;
	
	@Override
	public int createMenu(Menu menu) {
		try{
			menuDaoImpl.createMenu(menu);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
		
	}

	@Override
	public int updateMenu(Menu menu) {
		try{
			menuDaoImpl.updateMenu(menu);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public int deleteMenu(Long menuId) {
		try{
			menuDaoImpl.deleteMenu(menuId);
			return ResponseCodeUtil.DB_OPERATION_SUCCESS;
		}
		catch(Exception e){
			return ResponseCodeUtil.DB_OPERATION_FAILURE;
		}
	}

	@Override
	public List<Menu> getMenuList() {
		return menuDaoImpl.getMenuList();
	}

	@Override
	public Menu loadMenuById(int id) {
		return menuDaoImpl.loadMenuById(id);
	}

}
