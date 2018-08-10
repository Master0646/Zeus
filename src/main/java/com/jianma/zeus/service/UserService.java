package com.jianma.zeus.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.jianma.zeus.model.User;
import com.jianma.zeus.model.PageModel;


public interface UserService {
	
	public int createUser(User user);
	
	public int createManageUser(User user);
	
    public int updateUser(User user);
    public int deleteUser(Long userId);

    public int correlationRoles(int userId, List<Integer> roleIds);
    public int uncorrelationRoles(int userId, List<Integer> roleIds);

    public Optional<User> findOne(Long userId);

    public Optional<User> findByEmail(String username);

    public Set<String> findRoles(String username);

    public Set<Integer> findRolesIdByUserId(int userId);
    
    public Set<String> findPermissions(String username);
    
    public int updatePwd(String email, String password,String oldSlot);
    
    public int resetLoginUserPwd(String password);
    
    public Optional<User> checkAuthc(String email);
    
    public PageModel getUserByPage(int offset, int limit);
    
    public int updateValidSign(String email, int validValue);
    
    public int updateManageUser(User user); 
}
