package com.doremi.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.doremi.shop.order.dao.OrderDao;

import com.doremi.shop.order.vo.Order;
import com.doremi.shop.order.vo.OrderItem;
import com.doremi.shop.utils.PageBean;

//业务层要加事务
@Transactional
public class OrderService {
   private OrderDao orderDao;

public void setOrderDao(OrderDao orderDao) {
	this.orderDao = orderDao;
}
   public void save(Order order){
	   orderDao.save(order);
   }
   
  public PageBean<Order> findByPageUid(Integer u_id, Integer page) {
	// TODO Auto-generated method stub
	  
	  PageBean<Order> pageBean=new PageBean<Order>();
	  pageBean.setPage(page);
	  Integer limit=5;
	  pageBean.setLimit(limit);
	  
	  Integer totalCount=orderDao.findByCountUid(u_id);
	  pageBean.setTotalCount(totalCount);
	  
	  Integer totalPage;
	  if(totalCount%limit==0){
		  totalPage=totalCount/limit;
	  }else{
		  totalPage=totalCount/limit+1;
	  }
	  
	  pageBean.setTotalPage(totalPage);
	  
	  Integer begin=(page-1)*limit;
	  List<Order> list=orderDao.findByPageUid(u_id,begin,limit);
	  pageBean.setList(list);
	 	  
	  return pageBean;
   }
  
  public Order findByOid(Integer oid){
	return orderDao.findByOid(oid);
	  
  }
  
  public void update(Order order){
	  orderDao.update(order);
  }

  
	// 业务层查询所有订单方法
	public PageBean<Order> findAll(Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置参数
		pageBean.setPage(page);
		// 设置每页显示的记录数:
		int limit = 10;
		pageBean.setLimit(limit);
		// 设置总记录数
		int totalCount = orderDao.findCount();
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合
		int begin = (page - 1) * limit;
		List<Order> list = orderDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	// 业务层查询订单项的方法
	public List<OrderItem> findOrderItem(Integer oid) {
		return orderDao.findOrderItem(oid);
	}


	//带分页的根据订单状态查询
	public PageBean<Order> findByState(Integer page, Integer state) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// 设置当前页数:
		pageBean.setPage(page);
		// 设置每页显示记录数:
		// 显示5个
		int limit = 5;
		pageBean.setLimit(limit);
		// 设置总记录数:
		int totalCount = 0;
		totalCount = orderDao.findCountByState(state);
		pageBean.setTotalCount(totalCount);
		// 设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// 设置每页显示数据集合:
		int begin = (page - 1)*limit;
		List<Order> list = orderDao.findPageByState(state,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
}
