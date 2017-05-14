package com.doremi.shop.order.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.doremi.shop.cart.vo.Cart;
import com.doremi.shop.cart.vo.CartItem;
import com.doremi.shop.user.vo.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import com.doremi.shop.order.service.OrderService;
import com.doremi.shop.order.vo.Order;
import com.doremi.shop.order.vo.OrderItem;
import com.doremi.shop.utils.PageBean;
import com.doremi.shop.utils.PaymentUtil;


public class OrderAction extends ActionSupport implements ModelDriven<Order>{
  
	private Order order=new Order();
	private OrderService orderService=new OrderService();
	
	public Integer page;	
	public void setPage(Integer page) {
		this.page = page;
	}	
	@Override
	public Order getModel(){
		// TODO Auto-generated method stub
		return order;
	}
	
	private String province;
	public void setProvince(String province) {
		this.province = province;
	}
	
	private String city;
	public void setCity(String city) {
		this.city = city;
	}
	
	private String county;
	public void setCounty(String county) {
		this.county = county;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}
	public String save(){
		//获得session中购物车数据
		Cart cart=(Cart)ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart==null){
			this.addActionError("亲，您还没有购物！请先去购物!");
			return "msg";				
		}
		
		//生成订单数据
		order.setOrdertime(new Date());
		order.setState(1);//1.未付款  2.已付款，未发货  3.已经发货，未确认收货  4。交易完成
		
		order.setTotal(cart.getTotal());
		for (CartItem  cartItem : cart.getCartItems()) {
			OrderItem orderItem=new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			order.getOrderItems().add(orderItem);
		}
		
		User existUser=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser==null){
			this.addActionError("亲，您还没有登录！请先去登录!");
			return "login";	
		}
		order.setUser(existUser);
		orderService.save(order);
		cart.clearCart();
		return "saveSuccess";	
	}
	
	public String findByUid(){
		User user=(User)ServletActionContext.getRequest().getSession().getAttribute("existUser");
		PageBean<Order> pageBean=orderService.findByPageUid(user.getU_id(),page);
		
		//将数据存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		List<Order> nn=pageBean.getList();

		return "findByUidSuccess";
	}
	
	public String findByOid(){
		order=orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	
	public String payOrder() throws IOException{	
		
		Order currentOrder=orderService.findByOid(order.getOid());
		String addr = province+city+county+order.getAddr();
		currentOrder.setAddr(addr);
		currentOrder.setName(order.getName());
		currentOrder.setPhone(order.getPhone());
			currentOrder.setState(2);
			orderService.update(currentOrder);
		this.addActionMessage("֧付款成功!");
		return "msg";
	}
	
	
	// 修改订单的状态:
	public String updateState(){
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setState(4);
		orderService.update(currOrder);
		return "updateStateSuccess";
	}
}
