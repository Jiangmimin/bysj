package com.doremi.shop.order.adminaction;

import java.util.List;

import com.doremi.shop.order.service.OrderService;
import com.doremi.shop.order.vo.Order;
import com.doremi.shop.order.vo.OrderItem;
import com.doremi.shop.utils.PageBean;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;


public class AdminOrderAction extends ActionSupport implements ModelDriven<Order>{

	private Order order = new Order();

	public Order getModel() {
		return order;
	}

	private Integer page;
	
	public void setPage(Integer page) {
		this.page = page;
	}

	private OrderService orderService;

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}


	public String findAll(){
		
		PageBean<Order> pageBean = orderService.findAll(page);
		
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		
		return "findAll";
	}


	public String updateState(){
		
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(3);
		orderService.update(currOrder);
		
		return "updateStateSuccess";
	}
	
	
	public String findOrderItem(){
		
		List<OrderItem> list = orderService.findOrderItem(order.getOid());
		
		ActionContext.getContext().getValueStack().set("list", list);
		
		return "findOrderItem";
	}
	
	public String findByState(){
		PageBean<Order> pageBean=orderService.findByState(page,order.getState());
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByState";
	}
}
