package com.example.Tan2x.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.Tan2x.dto.ProductDTO;
import com.example.Tan2x.entities.Category;
import com.example.Tan2x.entities.Product;
import com.example.Tan2x.service.CategoryService;
import com.example.Tan2x.service.ProductService;

@Controller
public class AdminController {
	
	public static String uploadDir = System.getProperty("user.dir")+"/src/main/resources/static/productImages";
	public static String catUploadDir = System.getProperty("user.dir")+"/src/main/resources/static/images";
	@Autowired
	CategoryService categoryService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/admin")
	public String adminHome() {
		return "admin";
	}
	
	@GetMapping("/admin/category")
	public String adminCategory(Model model) {
		model.addAttribute("categories" , categoryService.getAllCategory());
		return "category";
	}
	
	@GetMapping("/admin/category/add")
	public String addCategory(Model model) {
		model.addAttribute("category", new Category());
		return "categoryAdd";
	}
	
	@PostMapping("/admin/category/add")
	public String postCategory(@ModelAttribute("category") Category category,
			@RequestParam("categoryImage") MultipartFile file,
			@RequestParam("imgName") String imgName) throws IOException{
		
		String imageUUID;
		if(!file.isEmpty()) {
			imageUUID=file.getOriginalFilename();
			Path fileNameAndPath = Paths.get(catUploadDir, imageUUID);
			Files.write(fileNameAndPath, file.getBytes());
		}
		
		else {
			imageUUID = imgName;
		}
		category.setImage(imageUUID);
		categoryService.addCategory(category);
		return "redirect:/admin/category";
	}
	
	@GetMapping("/admin/category/delete/{id}")
	public String deleteCategory(@PathVariable int id) {
		categoryService.removeCategoryById(id);
		return "redirect:/admin/category";
	}
	
	@GetMapping("/admin/category/update/{id}")
	public String updateCategory(@PathVariable int id, Model model) {
		Optional<Category> categoryOptional = categoryService.getCategoryById(id);
		if(categoryOptional.isPresent()) {
			model.addAttribute("category", categoryOptional.get());
			return "categoryAdd";
		}
		else return "404";
	}
	
	// Product Section
	
	@GetMapping("/admin/product")
	public String adminProduct(Model model) {
		model.addAttribute("products" , productService.getAllProduct());
		return "product";
	}
	
	@GetMapping("/admin/product/add")
	public String addProduct(Model model) {
		model.addAttribute("productDTO", new ProductDTO());
		model.addAttribute("categories", categoryService.getAllCategory());
		return "productadd";
	}
	
	@PostMapping("/admin/product/add")
	public String postProduct(@ModelAttribute("productDTO") ProductDTO productDTO, 
			@RequestParam("productImage1") MultipartFile file1,
			@RequestParam("productImage2") MultipartFile file2,
			@RequestParam("imgName1") String imgName1,
			@RequestParam("imgName2") String imgName2) throws IOException {
		
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setTitle(productDTO.getTitle());
		product.setPrice(productDTO.getPrice());
		product.setOffer(productDTO.isOffer());
		product.setView(productDTO.isView());
		product.setWeight(productDTO.getWeight());
		product.setDescription(productDTO.getDescription());
		product.setCategory(categoryService.getCategoryById(productDTO.getCategoryId()).get());
		
		String image1UUID;
		if(!file1.isEmpty()) {
			image1UUID=file1.getOriginalFilename();
			Path fileNameAndPath = Paths.get(uploadDir, image1UUID);
			Files.write(fileNameAndPath, file1.getBytes());
		}
		
		else {
			image1UUID = imgName1;
		}
		
		String image2UUID;
		if(!file2.isEmpty()) {
			image2UUID = file2.getOriginalFilename();
			Path fileNamePath = Paths.get(uploadDir, image2UUID);
			Files.write(fileNamePath, file2.getBytes());
		}
		
		else {
			image2UUID = imgName2;
		}
		
		product.setImage1(image1UUID);
		product.setImage2(image2UUID);
		productService.addProduct(product);
		
		return "redirect:/admin/product";
	}
	
	@GetMapping("/admin/product/delete/{id}")
	public String deleteProduct(@PathVariable long id) {
		productService.removeProductById(id);
		return "redirect:/admin/product";
	}
	
	@GetMapping("/admin/product/update/{id}")
	public String updateProduct(@PathVariable long id, Model model) {
		Product product = productService.getProductById(id).get();
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setTitle(product.getTitle());
		productDTO.setPrice(product.getPrice());
		productDTO.setOffer(product.isOffer());
		productDTO.setView(product.isView());
		productDTO.setWeight(product.getWeight());
		productDTO.setDescription(product.getDescription());
		productDTO.setCategoryId(product.getCategory().getId());
		productDTO.setImage1(product.getImage1());
		productDTO.setImage2(product.getImage2());
		
		model.addAttribute("productDTO", productDTO);
		model.addAttribute("categories", categoryService.getAllCategory());
		
		return "productAdd";
	}
}
