package com.example.Tan2x.controller;

import java.util.ArrayList;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

import org.aspectj.weaver.NewConstructorTypeMunger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.Tan2x.entities.Product;
import com.example.Tan2x.entities.Role;
import com.example.Tan2x.entities.User;
import com.example.Tan2x.service.CategoryService;
import com.example.Tan2x.service.ProductService;
import com.example.Tan2x.service.RoleService;
import com.example.Tan2x.service.UserService;

@Controller
public class HomeController {
	
	@Autowired
	CategoryService categoryService;
	@Autowired
	ProductService productService;
	@Autowired
	RoleService roleService;
	@Autowired
	UserService userService;
	
	@GetMapping({"/","/home"})
	public String homePage(Model model) {
		model.addAttribute("categories", categoryService.getAllCategory());
		return "index";
	}
	
	@GetMapping("/shop/{id}")
	public String shop(Model model, @PathVariable int id) {
		model.addAttribute("products", productService.getAllProductByCategoryId(id));
		model.addAttribute("categories", categoryService.getAllCategory());
		return "shop";
	}
	
	@GetMapping("/shop/category/{id}")
	public String productByCategory(Model model, @PathVariable int id) {
		model.addAttribute("categories", categoryService.getAllCategory());
		model.addAttribute("products", productService.getAllProductByCategoryId(id));
		return "shop";
	}
	
	@GetMapping("/shop/viewproduct/{id}")
	public String viewProduct(@PathVariable long id, Model model) {
		Product product= productService.getProductById(id).get();
		model.addAttribute(product);
		return "viewProduct";
	}
	
	@GetMapping("/loginUrl")
	public String login() {
		System.out.println("this is just for testing of the this login method is running or something else");
		return "login";
	}
	
	@PostMapping("/loginUrl")
	public String postLogin() {
		System.out.println("this is testing postlogin");
		return "redirect:/home";
	}
	
	@GetMapping("/register")
	public String register(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}
	
	@PostMapping("/register")
	public String postRegister(@ModelAttribute("user") User user) {
		User newUser = new User();
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		newUser.setPassword(user.getPassword());
		newUser.setMobile(user.getMobile());
		List<Role> roles=new ArrayList<>();
		roles.add(roleService.findRoleById(1).get());
		newUser.setRoles(roles);
		userService.addUser(newUser);
		return "redirect:/login";
	}
	
}
