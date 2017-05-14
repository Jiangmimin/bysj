package com.doremi.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.doremi.shop.adminuser.service.AdminUserService;
import com.doremi.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser> {


	private AdminUser adminUser=new AdminUser();
	public AdminUser getModel() {
		return adminUser;
	}
	

	private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}
	

	public String login(){
		AdminUser existAdminUser=adminUserService.login(adminUser);
		if(existAdminUser==null){
			
			this.addActionError("账户名或密码错误！");
			return "loginFail";
		}else{		
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}
	
	public String quit(){
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
