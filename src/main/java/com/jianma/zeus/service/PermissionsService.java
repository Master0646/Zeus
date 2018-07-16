package com.jianma.zeus.service;

import com.jianma.zeus.model.Permission;

public interface PermissionsService {

	public int createPermission(Permission permission);

	public int deletePermission(Long permissionId);
}
