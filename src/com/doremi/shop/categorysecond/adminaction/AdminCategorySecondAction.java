package com.doremi.shop.categorysecond.adminaction;

import java.util.List;

import com.doremi.shop.category.service.CategoryService;
import com.doremi.shop.category.vo.Category;
import com.doremi.shop.categorysecond.service.CategorySecondService;
import com.doremi.shop.categorysecond.vo.CategorySecond;
import com.doremi.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{


	private CategorySecond categorySecond=new CategorySecond();
	public CategorySecond getModel() {
		return categorySecond;
	}
	
	private CategorySecondService categorySecondService;
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	private Integer page;
	public void setPage(Integer page) {
		this.page = page;
	}

	private String key;
	public void setKey(String key) {
		this.key = key;
	}

	public String findAllByPage(){
		PageBean<CategorySecond> pageBean=categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}

	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	public String addPage(){
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);		
		return "addPage";
	}
	

	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	
	public String delete(){

		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	
	public String edit(){
	
		categorySecond=categorySecondService.findByCsid(categorySecond.getCsid());
		List<Category> cList=categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	
	public String update(){
		categorySecondService.update(categorySecond);
		return "update";
	}
	
	public String findByKey(){
		PageBean<CategorySecond> pageBean = categorySecondService.findByKey(key,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByKey";
	}

}
