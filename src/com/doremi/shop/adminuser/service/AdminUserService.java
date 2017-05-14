package com.doremi.shop.adminuser.service;

import org.springframework.transaction.annotation.Transactional;

import com.doremi.shop.adminuser.dao.AdminUserDao;
import com.doremi.shop.adminuser.vo.AdminUser;

@Transactional
public class AdminUserService {
	
	private AdminUserDao adminUserDao;
	public void setAdminUserDao(AdminUserDao adminUserDao) {
		this.adminUserDao = adminUserDao;
	}
	
	public AdminUser login(AdminUser adminUser) {
		return adminUserDao.login(adminUser);
	}
	

}
