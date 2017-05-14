package com.doremi.shop.category.adminaction;

import java.util.List;

import com.doremi.shop.category.service.CategoryService;
import com.doremi.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends ActionSupport implements
		ModelDriven<Category> {

	private Category category = new Category();
	public Category getModel() {
		return category;
	}

	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}
	
	private String key;
	public void setKey(String key) {
		this.key = key;
	}
	
	public String findAll() {
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
//	ת
	public String edit(){
		category=categoryService.findByCid(category.getCid());
		return "edit";
	}

	public String update(){
		categoryService.update(category);
		return "update";
	}

	public String delete(){

		category=categoryService.findByCid(category.getCid());
		categoryService.delete(category);
		return "deleteSuccess";
	}
	
	public String save(){
		categoryService.save(category);
		return "saveSuccess";
	}
	
	public String findByKey(){
		List<Category> cList = categoryService.findByKey(key);
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findByKey";
	}
}
