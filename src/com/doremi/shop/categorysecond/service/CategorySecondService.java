package com.doremi.shop.categorysecond.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.doremi.shop.categorysecond.dao.CategorySecondDao;
import com.doremi.shop.categorysecond.vo.CategorySecond;

import com.doremi.shop.utils.PageBean;

@Transactional
public class CategorySecondService {

	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		pageBean.setPage(page);
		pageBean.setLimit(10);
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		if (totalCount % 10 == 0) {
			totalPage = totalCount / 10;
		}
		else{
			totalPage = totalCount / 10 +1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*10;
		List<CategorySecond> list=categorySecondDao.findByPage(begin,10);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}

	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	public List<CategorySecond> findAll() {
		return categorySecondDao.findAll();
	}
	
	public PageBean<CategorySecond> findByKey(String key, Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		pageBean.setPage(page);
		pageBean.setLimit(10);
		int totalCount = categorySecondDao.findByKeyCount(key);
		pageBean.setTotalCount(totalCount);
		int totalPage=0;
		if (totalCount % 10 == 0) {
			totalPage = totalCount / 10;
		}
		else{
			totalPage = totalCount / 10 +1;
		}
		pageBean.setTotalPage(totalPage);
		int begin=(page-1)*10;
		List<CategorySecond> list=categorySecondDao.findByKey(key,begin,10);
		pageBean.setList(list);
		return pageBean;
	}
	
}
