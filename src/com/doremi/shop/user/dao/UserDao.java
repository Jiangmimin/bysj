package com.doremi.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.doremi.shop.user.vo.User;

import com.doremi.shop.utils.PageHibernateCallback;

/**
 * 用户模块持久层代码:
 *
 */
public class UserDao extends HibernateDaoSupport{

	// 按名次查询是否有该用户:
	public User findByUsername(String username){
		String hql = "from User where username = ?";
		System.out.println(username);
		List<User> list = this.getHibernateTemplate().find(hql, username);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	// 注册用户存入数据库代码实现
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	// 根据激活码查询用户
	public User findByCode(String code) {
		String hql = "from User where code = ?";
		List<User> list = this.getHibernateTemplate().find(hql,code);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	// 修改用户状态的方法
	public void update(User existUser) {
		this.getHibernateTemplate().update(existUser);
	}

	// 用户登录的方法
	public User login(User user) {
		String hql = "from User where username = ? and password = ? and state = ?";
		List<User> list = this.getHibernateTemplate().find(hql, user.getUsername(),user.getPassword(),1);
		if(list != null && list.size() > 0){
			return list.get(0);
		}
		return null;
	}
	
	public void delete(User existUser) {
		this.getHibernateTemplate().delete(existUser);
	}
	
	public User findByUid(Integer u_id) {
		System.out.println(u_id);
		return this.getHibernateTemplate().get(User.class,u_id);
	}
	
	public int findCount() {
		String hql="select count(*) from User";
		List<Long> list=this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}
	
	public List<User> findByPage(int begin, int limit) {
		String hql = "from User order by u_id";
		List<User> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<User>(hql, null,
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	public int findByKeyCount(String key) {
		String hql = "select count(*) from User where username like ?";
		String s = "%"+key+"%";
		System.out.println(s);
		List<Long> list = this.getHibernateTemplate().find(hql,s);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<User> findByKey(String key, int begin, int limit) {
		String hql = "from User where username like ?";
		String s = "%"+key+"%";
		System.out.println(s);
		List<User> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<User>(hql,new Object[]{s},
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
}
