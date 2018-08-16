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
	public void deleteRoleMenuByRoleId(Long roleId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete RoleMenu m  where m.roleId = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, roleId.intValue());
		query.executeUpdate();
	}

	@Override
	public List<Menu> getMenuListByRoleId(List<Integer> roleIds) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select m.name,m.url,rm.id,r.rolename,rm.roleId,rm.menuId from Role r,RoleMenu rm, Menu m "
				+ " where m.id = rm.menuId and rm.roleId = r.id and rm.roleId in (:roleId) order by rm.id asc ";
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
            int id = ((Number)o[2]).intValue();
            String roleName = (String)o[3];
            int menuId = ((Number)o[4]).intValue();
            int roleId = ((Number)o[5]).intValue();
            menu.setName(name);
            menu.setUrl(url);
            menu.setId(id);
            menu.setRoleName(roleName);
            menu.setMenuId(menuId);
            menu.setRoleId(roleId);
            mList.add(menu);
        }
        return mList;
	}

	@Override
	public RoleMenu loadRoleMenuById(int id) {
		Session session = sessionFactory.getCurrentSession();
		return (RoleMenu)session.get(RoleMenu.class, id);
	}

	@Override
	public void deleteRoleMenuByRoleMenuId(Long id) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete RoleMenu m  where m.id = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, id.intValue());
		query.executeUpdate();
	}

}
