package com.doremi.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.doremi.shop.utils.PageBean;

import com.doremi.shop.categorysecond.service.CategorySecondService;
import com.doremi.shop.categorysecond.vo.CategorySecond;
import com.doremi.shop.product.service.ProductService;
import com.doremi.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminProductAction extends ActionSupport implements
		ModelDriven<Product> {

	private Product product = new Product();

	public Product getModel() {
		return product;
	}

	private ProductService productService;

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	private Integer page;

	public void setPage(Integer page) {
		this.page = page;
	}
	
	private String key;
	public void setKey(String key) {
		this.key = key;
	}

	private CategorySecondService categorySecondService;

	public void setCategorySecondService(
			CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	private File upload;
	private String uploadFileName;
	private String uploadContentType;

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String findAllByPage() {
		PageBean<Product> pageBean = productService.findAllByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAllByPage";
	}

	public String addPage() {
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "addPage";
	}

	public String save() throws IOException {
		product.setPdate(new Date());
		if (upload != null) {

			String path = ServletActionContext.getServletContext().getRealPath(
					"/products/1");
	
			File diskFile = new File(path + "//" + uploadFileName);
		
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/1/" + uploadFileName);
		}
		productService.save(product);
		return "saveSuccess";
	}

	public String delete() {
	
		product = productService.findByPid(product.getPid());
		
		String path = product.getImage();
		if (path != null) {
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/" + path);
			File file = new File(realPath);
			file.delete();
		}
		
		productService.delete(product);
		return "deleteSuccess";
	}

	public String edit() {
		product = productService.findByPid(product.getPid());
		List<CategorySecond> csList = categorySecondService.findAll();
		ActionContext.getContext().getValueStack().set("csList", csList);
		return "editSuccess";
	}

	public String update() throws IOException {
	
		product.setPdate(new Date());
	
		if (upload != null) {
			String delPath = ServletActionContext.getServletContext()
					.getRealPath("/" + product.getImage());
			File file = new File(delPath);
			file.delete();
		
			String path = ServletActionContext.getServletContext().getRealPath(
					"/products/1");
		
			File diskFile = new File(path + "//" + uploadFileName);
			
			FileUtils.copyFile(upload, diskFile);

			product.setImage("products/1/" + uploadFileName);
		}
		
		productService.update(product);
		return "updatesucess";
	}
	
	public String findByKey(){
		
		PageBean<Product> pageBean = productService.findByKey(key,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByKey";
	}

}
