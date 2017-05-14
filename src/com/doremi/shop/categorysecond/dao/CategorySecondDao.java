package com.doremi.shop.categorysecond.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.doremi.shop.categorysecond.vo.CategorySecond;

import com.doremi.shop.utils.PageHibernateCallback;

public class CategorySecondDao extends HibernateDaoSupport {

	public int findCount() {
		String hql = "select count(*) from CategorySecond";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		this.getHibernateTemplate().afterPropertiesSet();

		System.out.println("hello");
		System.out.println("null");
		return 0;
	}

	public List<CategorySecond> findByPage(int begin, int i) {
		String hql = "from CategorySecond order by csid desc";
		List<CategorySecond> list = this.getHibernateTemplate()
				.execute(
						new PageHibernateCallback<CategorySecond>(hql, null,
								begin, 10));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}

	public void save(CategorySecond categorySecond) {
		this.getHibernateTemplate().save(categorySecond);
		
	}

	public CategorySecond findByCsid(Integer csid) {
		return this.getHibernateTemplate().get(CategorySecond.class, csid);
	}

	public void delete(CategorySecond categorySecond) {
		this.getHibernateTemplate().delete(categorySecond);
		
	}

	public void update(CategorySecond categorySecond) {
		this.getHibernateTemplate().update(categorySecond);
	}

	public List<CategorySecond> findAll() {
		String hql = "from CategorySecond";
		List<CategorySecond> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	public int findByKeyCount(String key) {
		String hql = "select count(*) from CategorySecond where csname like ?";
		String s = "%"+key+"%";
		System.out.println(s);
		List<Long> list = this.getHibernateTemplate().find(hql,s);
		if(list!=null&&list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<CategorySecond> findByKey(String key, int begin, int i) {
		String hql = "from CategorySecond where csname like ?";
		String s = "%"+key+"%";
		System.out.println(s);
		List<CategorySecond> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<CategorySecond>(hql,new Object[]{s},
						begin, 10));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}


}
