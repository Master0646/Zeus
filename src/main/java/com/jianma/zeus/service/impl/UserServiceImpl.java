package com.jianma.zeus.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.zeus.dao.UserDao;
import com.jianma.zeus.model.Role;
import com.jianma.zeus.model.User;
import com.jianma.zeus.model.PageModel;
import com.jianma.zeus.model.UserRole;
import com.jianma.zeus.service.UserService;
import com.jianma.zeus.util.PasswordHelper;
import com.jianma.zeus.util.ResponseCodeUtil;


@Service
@Component
@Qualifier(value = "userServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	@Qualifier(value = "userDaoImpl")
	private UserDao userDaoImpl;
	
	@Override
	public int createUser(User user) {
		try {
			Optional<User> optUser = userDaoImpl.findByEmail(user.getEmail());

			if (optUser.isPresent()) {
				return ResponseCodeUtil.UESR_CREATE_EXIST;
			} else {
				user.setValid((byte)1);
				PasswordHelper.encryptAppPassword(user);
				Set<UserRole> userRoles = new HashSet<>();
				UserRole userRole = new UserRole();
				userRole.setUser(user);
				Role role = new Role();
				role.setId(1);
				userRole.setRole(role);
				userRoles.add(userRole);
				user.setUserRoles(userRoles);
				user.setCreatetime(new Date());
				userDaoImpl.createUser(user);

				return ResponseCodeUtil.UESR_OPERATION_SUCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public int updateUser(User user) {
		try {
			PasswordHelper.encryptAppPassword(user);
			userDaoImpl.updateUser(user);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}

	}

	@Override
	public int deleteUser(Long userId) {
		try {
			userDaoImpl.deleteUser(userId);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public int correlationRoles(int userId, List<Integer> roleIds) {
		try {
			userDaoImpl.correlationRoles(userId, roleIds);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}

	}

	@Override
	public int uncorrelationRoles(int userId, List<Integer> roleIds) {
		try {
			userDaoImpl.uncorrelationRoles(userId, roleIds);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}

	}

	@Override
	public Optional<User> findOne(Long userId) {
		return userDaoImpl.findOne(userId);
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return userDaoImpl.findByEmail(email);
	}

	@Override
	public Set<String> findRoles(String username) {

		return userDaoImpl.findRoles(username);
	}

	@Override
	public Set<String> findPermissions(String username) {

		return userDaoImpl.findPermissions(username);
	}


	@Override
	public int updatePwd(String email, String password,String oldSlot) {
		try {

			User user = new User();
			user.setPassword(password);
			user.setEmail(email);
			PasswordHelper.encryptAppPassword(user);

			userDaoImpl.updatePwd(email, user.getPassword(), oldSlot, user.getSlot());
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}
	
	@Override
	public int resetLoginUserPwd(String password) {
		try {
			Subject subject = SecurityUtils.getSubject();
			String email = subject.getSession().getAttribute("email").toString();
			
			User user = new User();
			user.setPassword(password);
			user.setEmail(email);
			PasswordHelper.encryptAppPassword(user);

			userDaoImpl.resetLoginUserPwd(email, user.getPassword(), user.getSlot());
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public Optional<User> checkAuthc(String email) {
		// TODO Auto-generated method stub
		return userDaoImpl.checkAuthc(email);
	}

	@Override
	public PageModel getUserByPage(int offset, int limit) {
		List<User> list = userDaoImpl.findUserListByPage(offset, limit);
		int count = userDaoImpl.getCountUser();
		PageModel userPageModel = new PageModel();
		userPageModel.setCount(count);
		userPageModel.setList(list);
		return userPageModel;
	}

	@Override
	public int updateValidSign(String email, int validValue) {
		try {
			userDaoImpl.updateValidSign(email, validValue);
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public int createManageUser(User user) {
		try {
			Optional<User> optUser = userDaoImpl.findByEmail(user.getEmail());

			if (optUser.isPresent()) {
				return ResponseCodeUtil.UESR_CREATE_EXIST;
			} else {
				user.setValid((byte)1);
				PasswordHelper.encryptAppPassword(user);
				user.setCreatetime(new Date());
				Set<UserRole> set = user.getUserRoles();
				for (UserRole ur : set){
					ur.setUser(user);
				}
				userDaoImpl.createUser(user);

				return ResponseCodeUtil.UESR_OPERATION_SUCESS;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

	@Override
	public Set<Integer> findRolesIdByUserId(int userId) {
		return userDaoImpl.findRolesIdByUserId(userId);
	}

	@Override
	public int updateManageUser(User user) {
		try {
			PasswordHelper.encryptAppPassword(user);
			userDaoImpl.updateUser(user);
			Set<UserRole> userRoles = user.getUserRoles();
			List<Integer> roleIds = new ArrayList<>();
			for (UserRole userRole : userRoles){
				roleIds.add(userRole.getRole().getId());
			}
			userDaoImpl.deleteRolesByUserId(user.getId());
			if (roleIds.size() > 0){
				userDaoImpl.correlationRoles(user.getId(), roleIds);
			}
			
			return ResponseCodeUtil.UESR_OPERATION_SUCESS;
		} catch (Exception e) {
			return ResponseCodeUtil.UESR_OPERATION_FAILURE;
		}
	}

}
