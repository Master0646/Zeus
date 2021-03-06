package com.jianma.zeus.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jianma.zeus.dao.RoleDao;
import com.jianma.zeus.model.Role;
import com.jianma.zeus.service.RoleService;


@Service
@Component
@Qualifier(value = "roleServiceImpl")
@Transactional
public class RoleServiceImpl implements RoleService {

	@Autowired
	@Qualifier(value = "roleDaoImpl")
	private RoleDao roleDaoImpl;
	
	@Override
	public void createRole(Role role) {
		
		roleDaoImpl.createRole(role);
	}

	@Override
	public void deleteRole(Long roleId) {
		roleDaoImpl.deleteRole(roleId);
	}

	@Override
	public void correlationPermissions(Long roleId, Long... permissionIds) {
		roleDaoImpl.correlationPermissions(roleId, permissionIds);
	}

	@Override
	public void uncorrelationPermissions(Long roleId, Long... permissionIds) {
		roleDaoImpl.uncorrelationPermissions(roleId, permissionIds);
	}

	@Override
	public List<Role> getAllRoles() {
		List<Role> listRole = roleDaoImpl.getAllRoles();
		
		List<Role> returnList = new ArrayList<>();
		Role resultRole = null;
		for (Role role : listRole){
			resultRole = new Role();
			resultRole.setRolename(role.getRolename());
			resultRole.setId(role.getId());
			returnList.add(resultRole);
		}
		return returnList;
	}

}
