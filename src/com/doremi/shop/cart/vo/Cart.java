package com.doremi.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
   private Map<Integer,CartItem> map=new LinkedHashMap<Integer,CartItem>();
   private double total;
   
   public Collection<CartItem> getCartItems(){
	   return map.values();
   }
   
   public void addCart(CartItem cartitem){
	   Integer pid=cartitem.getProduct().getPid();
	   //判断是否存在该类型的商品
	   if(map.containsKey(pid)){
		   CartItem cartItem=map.get(pid);
		   cartItem.setCount(cartItem.getCount()+cartitem.getCount());
	   }
	   else{
		   map.put(pid, cartitem);
	   }
	   total+=cartitem.getSubtotal();
   }
   
   public void removeCart(Integer pid){
	   CartItem cartitem=map.remove(pid);
	   total-=cartitem.getSubtotal();
   }
   
   public void clearCart(){
	   map.clear();
	   total=0;
   }
   
public Map<Integer, CartItem> getMap() {
	return map;
}
public void setMap(Map<Integer, CartItem> map) {
	this.map = map;
}
public double getTotal() {
	return total;
}
	
}
