package com.doremi.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.doremi.shop.order.vo.Order;
import com.doremi.shop.order.vo.OrderItem;
import com.doremi.shop.utils.PageHibernateCallback;

public class OrderDao extends HibernateDaoSupport{
	
   public void save(Order order){
	   this.getHibernateTemplate().save(order);
   }

public Integer findByCountUid(Integer u_id) {
	// TODO Auto-generated method stub
	String hql="select count(*) from Order o where o.user.u_id=?";
	List<Long> list=this.getHibernateTemplate().find(hql,u_id);
	
	if(list!=null&&list.size()>0){
		return list.get(0).intValue();
	}
	return null;
}

public List<Order> findByPageUid(Integer u_id, Integer begin, Integer limit) {
	// TODO Auto-generated method stub
	String hql="from Order o where o.user.u_id=? order by ordertime desc";
	List<Order> list=this.getHibernateTemplate().execute(new PageHibernateCallback<Order>(hql, new Object[]{u_id}, begin, limit));
	return list;
}

public Order findByOid(Integer oid){
	return this.getHibernateTemplate().get(Order.class, oid);
	
}

public void update(Order order){
	this.getHibernateTemplate().update(order);
	
}

    //DAO中统计订单个数的方法
	public int findCount() {
		String hql = "select count(*) from Order";
		List<Long> list = this.getHibernateTemplate().find(hql);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// DAO中分页查询订单的方法
	public List<Order> findByPage(int begin, int limit) {
		String hql = "from Order order by ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, null, begin, limit));
		return list;
	}

	// DAo中根据订单id查询订单项
	public List<OrderItem> findOrderItem(Integer oid) {
		String hql = "from OrderItem oi where oi.order.oid = ?";
		List<OrderItem> list = this.getHibernateTemplate().find(hql, oid);
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
	
	public int findCountByState(Integer state) {
		String hql = "select count(*) from Order o where o.state = ?";
		List<Long> list = this.getHibernateTemplate().find(hql, state);
		if (list != null && list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	public List<Order> findPageByState(Integer state, int begin, int limit) {
		String hql = "from Order o where o.state = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[] { state },
						begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return null;
	}
}
