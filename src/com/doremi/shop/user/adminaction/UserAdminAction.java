package com.doremi.shop.user.adminaction;

import com.doremi.shop.utils.PageBean;

import com.doremi.shop.user.service.UserService;
import com.doremi.shop.user.vo.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAdminAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	public User getModel() {
		return user;
	}
	
	private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}

	private String key;
	public void setKey(String key) {
		this.key = key;
	}
	
	public String findAll(){
		PageBean<User> pageBean = userService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	public String delete(){
		User existUser = userService.findByUid(user.getU_id());
		userService.delete(existUser);
		return "deleteSuccess";
	}
	
	public String edit(){
		user = userService.findByUid(user.getU_id());
		return "editSuccess";
	}
	
	public String update(){
		userService.update(user);
		return "updateSuccess";
	}
	//通过输入的key模糊查询
	public String findByKey(){
		System.out.println("查询了~~~~~~~~~~~~~~~~~~~~~~~~~~");
		PageBean<User> pageBean = userService.findByKey(key,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByKey";
	}
}
