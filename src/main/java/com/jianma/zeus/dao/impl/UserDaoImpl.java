package com.jianma.zeus.dao.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.jianma.zeus.dao.UserDao;
import com.jianma.zeus.model.Role;
import com.jianma.zeus.model.User;
import com.jianma.zeus.model.UserRole;


@Repository
@Component
@Qualifier(value = "userDaoImpl")
public class UserDaoImpl implements UserDao {

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	@Override
	public void createUser(User user) {
		sessionFactory.getCurrentSession().save(user);
	}

	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " update user u set u.realname = ?, u.mobile = ?, u.address = ?, u.email = ?, "
				+ " u.school = ?, u.academy = ?, u.nickname = ?,u.password = ? where u.id = ? ";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, user.getRealname());
		query.setParameter(1, user.getMobile());
		query.setParameter(2, user.getAddress());
		query.setParameter(3, user.getEmail());
		query.setParameter(4, user.getSchool());
		query.setParameter(5, user.getAcademy());
		query.setParameter(6, user.getNickname());
		query.setParameter(7, user.getPassword());
		query.setParameter(8, user.getId());
		query.executeUpdate();
	}

	@Override
	public void deleteUser(Long userId) {

		Session session = sessionFactory.getCurrentSession();
		String sql = " update user u set u.valid = 0 where u.id = ?";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, userId);
		query.executeUpdate();

	}

	@Override
	public void correlationRoles(int userId, List<Integer> roleIds) {
		Session session = sessionFactory.getCurrentSession();
		User user = new User();
		user.setId(userId);

		for (Integer roleId : roleIds) {
			UserRole userRole = new UserRole();
			Role role = new Role();
			role.setId(roleId.intValue());
			userRole.setUser(user);
			userRole.setRole(role);
			session.save(userRole);
		}
	}

	@Override
	public void uncorrelationRoles(int userId, List<Integer> roleIds) {
		Session session = sessionFactory.getCurrentSession();
		User user = new User();
		user.setId(userId);

		for (Integer roleId : roleIds) {
			UserRole userRole = new UserRole();
			Role role = new Role();
			role.setId(roleId.intValue());
			userRole.setUser(user);
			userRole.setRole(role);
			session.delete(userRole);
		}
	}

	@Override
	public Optional<User> findOne(Long userId) {
		User user = (User) sessionFactory.getCurrentSession().load(User.class, userId.intValue());
		return Optional.ofNullable(user);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " from User u where u.email = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, email);
		List<User> list = query.list();
		if (list.size() > 0) {
			Optional<User> user = Optional.ofNullable(list.get(0));
			return user;
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Set<String> findRoles(String username) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select rolename from user u, user_role ur,role r where u.email = ? and u.id = ur.userId and r.id = ur.roleId";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, username);
		Set<String> set=new HashSet<String>();
		set.addAll(query.list());
		return set;
	}

	@Override
	public Set<String> findPermissions(String username) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select permission_name from user u, user_role ur,role r,permission_role pr,permission p where u.email = ? and u.id = ur.userId and r.id = ur.roleId "
				+ "and r.id = pr.roleId and pr.permissionId = p.id ";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, username);
		Set<String> set=new HashSet<String>();
		set.addAll(query.list());
		return set;
	}

	@Override
	public void updateValidSign(String email, int validValue) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " update user u set u.valid = ? where u.email = ? ";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, validValue);
		query.setParameter(1, email);
		query.executeUpdate();
		
	}

	@Override
	public void updateActiveSign(String email) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " update user u set u.activesign = 1 where u.email = ? ";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, email);
		query.executeUpdate();
	}

	@Override
	public void updatePwd(String email, String password, String oldSlot,String newSlot) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " update user u set u.password = ?, u.slot = ? where u.email = ? and u.slot = ?";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, password); 
		query.setParameter(1, newSlot); 
        query.setParameter(2, email); 
        query.setParameter(3, oldSlot);
		query.executeUpdate();
	}

	@Override
	public Optional<User> checkAuthc(String email) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select u.id,u.email, u.nickname,u.realname,u.password, s.code from User u, School s where u.email = ? and u.school = s.id ";
		Query query = session.createQuery(hql);
		query.setParameter(0, email);
		
		List list = query.list();
		
		if (list.size() > 0) {
			Object []o = (Object[])list.get(0);
			int id = ((Number)o[0]).intValue();
			String e_mail = (String)o[1];
			String nickname = (String)o[2];
            String realname = (String)o[3];
            String password = (String)o[4];
            String code = (String)o[5];
            User user = new User();
            user.setId(id);
            user.setEmail(e_mail);
            user.setNickname(nickname);
            user.setRealname(realname);
            user.setPassword(password);
            user.setSchoolCode(code);
			Optional<User> userObj = Optional.ofNullable(user);
			return userObj;
		} else {
			return Optional.empty();
		}
	}

	@Override
	public void resetLoginUserPwd(String email, String password, String slot) {
		Session session = sessionFactory.getCurrentSession();
		String hql = " update User u set u.password = ?, u.slot = ? where u.email = ?";
		Query query = session.createQuery(hql);
		query.setParameter(0, password); 
		query.setParameter(1, slot); 
        query.setParameter(2, email); 
		query.executeUpdate();
	}

	@Override
	public List<User> findUserListByPage(int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String hql  = "select email,realname,mobile,address,valid,id,school,academy from User where valid = 1 order by createtime desc";
		Query query = session.createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(limit);
		List list = query.list();
		
		List<User> userList = new ArrayList<User>(10);
        User user = null;
        for(int i=0;i<list.size();i++)
        {
        	user = new User();
            Object []o = (Object[])list.get(i);
            String email = (String)o[0];
            String realname = (String)o[1];
            String mobile = (String)o[2];
            String address = (String)o[3];
            int valid = ((Number)o[4]).intValue();
            int id = ((Number)o[5]).intValue();
            short school = ((Number)o[6]).shortValue();
            short academy = ((Number)o[7]).shortValue();
            
            user.setEmail(email);
            user.setRealname(realname);
            user.setMobile(mobile);
            user.setAddress(address);
            user.setValid((byte)valid);
            user.setId(id);
            user.setSchool(school);
            user.setAcademy(academy);
            
            userList.add(user);
        }
        return userList;
	}

	@Override
	public int getCountUser() {
		Session session = sessionFactory.getCurrentSession();
		String hql = " select count(u) from User u where u.valid = 1 ";
		Query query = session.createQuery(hql);
        return (int)((Long)query.uniqueResult()).longValue();
	}

	@Override
	public void updateJudgePwd(String email, String password, String slot) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " update user u set u.password = ?, u.slot = ? where u.email = ? ";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, password); 
		query.setParameter(1, slot); 
        query.setParameter(2, email); 
		query.executeUpdate();
	}

	@Override
	public void deleteUserByEmail(String email) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " delete from User u where u.email = ? ";
		Query query = session.createQuery(sql);
		query.setParameter(0, email);
		query.executeUpdate();
	}

	@Override
	public Set<Integer> findRolesIdByUserId(int userId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = "select roleId from user_role ur where ur.userId = ? ";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, userId);
		Set<Integer> set=new HashSet<Integer>();
		set.addAll(query.list());
		return set;
	}

	@Override
	public void deleteRolesByUserId(int userId) {
		Session session = sessionFactory.getCurrentSession();
		String sql = " delete from user_role  where  userId = ? ";
		Query query = session.createSQLQuery(sql);
		query.setParameter(0, userId);
		query.executeUpdate();
	}

	
}
