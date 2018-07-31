package com.jianma.zeus.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.zeus.dao.RoleMenuDao;
import com.jianma.zeus.model.Menu;
import com.jianma.zeus.model.RoleMenu;


@Repository
@Component
@Qualifier(value = "roleMenuDaoImpl")
public class RoleMenuDaoImpl implements RoleMenuDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createRoleMenu(RoleMenu roleMenu) {
		sessionFactory.getCurrentSession().save(roleMenu);
	}

	@Override
	public void deleteRoleMenu(Long roleId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete RoleMenu m  where m.roleId = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, roleId);
		query.executeUpdate();
	}

	@Override
	public List<Menu> getMenuListByRoleId(List<Integer> roleIds) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select m.name,m.url from RoleMenu rm, Menu m "
				+ " where m.id = rm.menuId and rm.roleId in (:roleId)";
		Query query = session.createQuery(hql);
		query.setParameterList("roleId", roleIds);
		List list = query.list();
		List<Menu> mList = new ArrayList<Menu>();
		Menu menu = null;
        for(int i = 0; i < list.size(); i++)
        {
        	menu = new Menu();
            Object []o = (Object[])list.get(i);
          
            String name = (String)o[0];
            String url = (String)o[1];
            
            menu.setName(name);
            menu.setUrl(url);
            
            mList.add(menu);
        }
        return mList;
	}

}
