package com.doremi.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.doremi.shop.category.vo.Category;

public class CategoryDao extends HibernateDaoSupport{
    
	public List<Category> findAll(){
		String hql="from Category order by cid";
		List<Category> list=this.getHibernateTemplate().find(hql);
		return list;
	}
	
	

	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}


	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
		
	}

	
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}
	
	public List<Category> findByKey(String key) {
		String hql = "from Category where cname like ?";
		String s = "%"+key+"%";
		List<Category> list = this.getHibernateTemplate().find(hql, s);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
