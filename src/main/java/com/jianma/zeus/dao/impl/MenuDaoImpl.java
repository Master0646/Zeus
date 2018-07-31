package com.jianma.zeus.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.zeus.dao.MenuDao;
import com.jianma.zeus.model.Menu;

@Repository
@Component
@Qualifier(value = "menuDaoImpl")
public class MenuDaoImpl implements MenuDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	@Override
	public void createMenu(Menu menu) {
		sessionFactory.getCurrentSession().save(menu);
	}

	@Override
	public void updateMenu(Menu menu) {
		sessionFactory.getCurrentSession().update(menu);
	}

	@Override
	public void deleteMenu(Long menuId) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " delete Menu m  where m.id = ? ";
		Query query = session.createQuery(hql);
		query.setParameter(0, menuId);
		query.executeUpdate();
	}

	@Override
	public List<Menu> getMenuList() {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from Menu m order by updateAt desc";
		Query query = session.createQuery(hql);
		return query.list();
	}

}
