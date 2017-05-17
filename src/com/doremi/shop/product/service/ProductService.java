package com.doremi.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.doremi.shop.product.dao.ProductDao;
import com.doremi.shop.product.vo.Product;

import com.doremi.shop.utils.PageBean;

@Transactional
public class ProductService {
	// 注入ProductDao
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	// 首页上热门商品查询
	public List<Product> findHot() {
		return productDao.findHot();
	}

	// 首页上最新商品的查询
	public List<Product> findNew() {
		return productDao.findNew();
	}

	//首页推荐商品
	public List<Product> findRecommender() {

		return productDao.findRecommender();
	}
	// 根据商品ID查询商品
	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}
	//根据商品名称查找
	public PageBean<Product> findByName(String searchText, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
	
		pageBean.setPage(page);
		int limit = 8;
		pageBean.setLimit(limit);

		int totalCount = productDao.findByName(searchText);
		pageBean.setTotalCount(totalCount);
		int totalPage = (int) Math.ceil(((double)totalCount)/limit);
		pageBean.setTotalPage(totalPage);

		int begin = (page-1)*limit;
		List<Product> list = productDao.findByPageName(searchText,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	// 根据一级分类的cid带有分页查询商品
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 8;
		pageBean.setLimit(limit);
		//  设置总记录数:
		int totalCount = 0 ;
//		System.out.print(cid);
		totalCount = productDao.findCountCid(cid);
		System.out.println(totalCount);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
//		System.out.println(list.size());
		return pageBean;
	}

	// 根据二级分类查询商品信息
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		int limit = 8;
		pageBean.setLimit(limit);
		//  设置总记录数:
		int totalCount = 0 ;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		// 设置总页数:
		int totalPage = 0;
		// Math.ceil(totalCount / limit);
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 每页显示的数据集合:
		// 从哪开始:
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	public PageBean<Product> findAllByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		pageBean.setPage(page);
	
		int limit = 10;
		pageBean.setLimit(limit);
		
		int totalCount = 0 ;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);

		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
	
		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(Product product) {
		productDao.save(product);
		
	}

	public void delete(Product product) {
		productDao.delete(product);
	}

	public void update(Product product) {
		productDao.update(product);
	}
	
	public PageBean<Product> findByKey(String key, Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();

		pageBean.setPage(page);

		int limit = 10;
		pageBean.setLimit(limit);

		int totalCount = 0 ;
		totalCount = productDao.findByKeyCount(key);
		pageBean.setTotalCount(totalCount);

		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);

		int begin = (page - 1) * limit;
		List<Product> list = productDao.findByKey(key,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
}
