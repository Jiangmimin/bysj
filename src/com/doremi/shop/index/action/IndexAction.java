package com.doremi.shop.index.action;

import java.util.List;

import com.doremi.shop.category.service.CategoryService;
import com.doremi.shop.category.vo.Category;
import com.doremi.shop.product.service.ProductService;
import com.doremi.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.HttpServletRequest;


public class IndexAction extends ActionSupport{

	   private CategoryService categoryService;

	   public void setCategoryService(CategoryService categoryService) {
	   	this.categoryService = categoryService;
	   }
	   private ProductService productService;
	   
	   public void setProductService(ProductService productService) {
		   	this.productService = productService;
		   }
	   
	public String execute(){
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getSession().put("cList", cList);
		List<Product> hList=productService.findHot();
		ActionContext.getContext().getValueStack().set("hList",hList);
		List<Product> nList=productService.findNew();
		ActionContext.getContext().getValueStack().set("nList",nList);
//		List<Product> rList=productService.findRecommender();
//		ActionContext.getContext().getValueStack().set("rList",rList);
		return "index";
	}
	
	
}
