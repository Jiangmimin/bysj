package com.doremi.shop.user.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.doremi.shop.user.dao.UserDao;
import com.doremi.shop.user.vo.User;

import com.doremi.shop.utils.MailUtils;
import com.doremi.shop.utils.PageBean;
import com.doremi.shop.utils.UUIDUtils;

/**
 * 用户名模块业务层代码
 *
 */
@Transactional
public class UserService {
	// 注入UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	
	// 按用户名查询用户的方法:
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}

	// 业务层完成用户注册代码:
	public void save(User user){
		// 将数据存入到数据库
		user.setState(0); // 0:代表用户未激活.  1:代表用户已经激活.
		String code = UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
		// 发送激活邮件;
		MailUtils mail=new MailUtils();
		System.out.println(user.getEmail()+"//"+code);
		try {
			mail.sendMail(user.getEmail(), code);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("dd");
		}
	}

	// 业务层根据激活码查询用户
	public User findByCode(String code) {
		return userDao.findByCode(code);
	}

	// 修改用户的状态的方法
	public void update(User existUser) {
		userDao.update(existUser);
	}

	// 用户登录的方法
	public User login(User user) {
		return userDao.login(user);
	}
	
	public PageBean<User> findByPage(Integer page) {
		PageBean<User> pageBean = new PageBean<User>();
		
		pageBean.setPage(page);
		
		int limit = 10;
		pageBean.setLimit(limit);
		
		int totalCount = 0 ;
		totalCount = userDao.findCount();
		pageBean.setTotalCount(totalCount);		
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);		
		int begin = (page - 1) * limit;
		List<User> list = userDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	public User findByUid(Integer u_id) {
		return userDao.findByUid(u_id);
	}

	public void delete(User existUser) {
		userDao.delete(existUser);
		
	}

	public PageBean<User> findByKey(String key,Integer page) {
		PageBean<User> pageBean = new PageBean<User>();

		pageBean.setPage(page);

		int limit = 10;
		pageBean.setLimit(limit);

		int totalCount = 0 ;
		totalCount = userDao.findByKeyCount(key);
		pageBean.setTotalCount(totalCount);

		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);

		int begin = (page - 1) * limit;
		List<User> list = userDao.findByKey(key,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	
}
