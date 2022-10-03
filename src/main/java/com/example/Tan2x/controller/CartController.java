package com.example.Tan2x.controller;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Tan2x.dto.OrderDetailsDTO;
import com.example.Tan2x.entities.Cart;
import com.example.Tan2x.entities.OrderItem;
import com.example.Tan2x.entities.Orders;
import com.example.Tan2x.entities.Paytm;
import com.example.Tan2x.entities.Product;
import com.example.Tan2x.service.CartService;
import com.example.Tan2x.service.CategoryService;
import com.example.Tan2x.service.OrderItemService;
import com.example.Tan2x.service.OrderService;
import com.example.Tan2x.service.ProductService;

import com.paytm.pg.merchant.PaytmChecksum;

@Controller
public class CartController {

	@Autowired
	CartService cartService;
	@Autowired
	ProductService productService;
	@Autowired
	OrderItemService orderItemService;
	@Autowired
	OrderService orderService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	Paytm paytmDetails;
	@Autowired
	org.springframework.core.env.Environment env;
	
	@GetMapping("/cart")
	public String cartView(Model model) {
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		String userName=authentication.getName();
		List<Cart> cartItem=cartService.cartItemByUserName(userName);
		int totalCost=0;
		for(Cart cart : cartItem) {
			totalCost+=cart.getQuantity()*cart.getPrice();
		}
		int deliveryCharge=0;
		if(totalCost<200) deliveryCharge=20;
		totalCost+=deliveryCharge;
		model.addAttribute("deliveryCharge", deliveryCharge);
		model.addAttribute("totalCost" , totalCost);
		model.addAttribute("cartItem", cartItem);
		return "cartSummary";
	}
	
	@GetMapping("/addToCart/{id}")
	public String buy(@PathVariable long id,Model model) {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		List<Cart> cartItems = cartService.cartItemByUserName(userName);
		boolean flag=true;
		for(Cart cart : cartItems) {
			if(cart.getProductId()==id) {
				cart.setQuantity(cart.getQuantity()+1);
				cartService.addCart(cart);
				flag = false;
				break;
			}
		}
		if(flag) {
			Product product = productService.getProductById(id).get();
			Cart cart = new Cart();
			cart.setProductId(id);
			cart.setProductTitle(product.getTitle());
			cart.setPrice(product.getPrice());
			cart.setUserName(userName);
			cart.setQuantity(1);
			cart.setImage(product.getImage1());
			cartService.addCart(cart);
			cartItems.add(cart);
		}
		model.addAttribute("cartItem", cartItems);
		return "redirect:/cart";
	}
	
	@GetMapping("/cart/viewProduct/{id}")
	public String addToCart(@PathVariable long id,Model model) {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		List<Cart> cartItems = cartService.cartItemByUserName(userName);
		boolean flag=true;
		for(Cart cart : cartItems) {
			if(cart.getProductId()==id) {
				cart.setQuantity(cart.getQuantity()+1);
				cartService.addCart(cart);
				flag = false;
				break;
			}
		}
		if(flag) {
			Product product = productService.getProductById(id).get();
			Cart cart = new Cart();
			cart.setProductId(id);
			cart.setProductTitle(product.getTitle());
			cart.setPrice(product.getPrice());
			cart.setUserName(userName);
			cart.setQuantity(1);
			cart.setImage(product.getImage1());
			cartService.addCart(cart);
			cartItems.add(cart);
		}
		Product product= productService.getProductById(id).get();
		model.addAttribute(product);
		return "redirect:/shop/viewproduct/{id}";
	}
	
	@GetMapping("/cart/single/remove/{id}")
	public String removeSingle(@PathVariable long id, Model model) {
		Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		List<Cart> cartItems = cartService.cartItemByUserName(userName);
		for(Cart cart : cartItems) {
			if(cart.getProductId()==id) {
				if(cart.getQuantity()==1) {
					cartService.deleteCartById(cart.getId());
				}
				else {
					cart.setQuantity(cart.getQuantity()-1);
					cartService.addCart(cart);
				}
				break;
			}
		}
		cartItems = cartService.cartItemByUserName(userName);
		model.addAttribute("cartItem", cartItems);
		return "redirect:/cart";
	}
	
	@GetMapping("cart/delete/{id}")
	public String deleteCart(@PathVariable long id, Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		List<Cart> cartItems = cartService.cartItemByUserName(userName);
		for(Cart cart : cartItems) {
			if(cart.getProductId()==id) {
				cartService.deleteCartById(id);
				break;
			}
		}
		cartItems = cartService.cartItemByUserName(userName);
		model.addAttribute("cartItem" , cartItems);
		return "redirect:/cart";
	}
	
	@GetMapping("/checkout")
	public String checkout(Model model) {
		System.out.println("this si inside checkout");
		model.addAttribute("orderDetailsDTO", new OrderDetailsDTO());
		return "checkout";
	}
	
	@PostMapping("/checkout")
	public String placeOrder(@ModelAttribute("orderDetailsDTO") OrderDetailsDTO orderDetailsDTO,Model model,
			@RequestParam("payment_option") String paymentMood) throws Exception { 
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userName = authentication.getName();
		
		Orders order =new Orders();
		order.setUserName(userName);
		order.setName(orderDetailsDTO.getName());
		order.setOrderd(false);
		order.setAddress(orderDetailsDTO.getAddress());
		order.setCancelled(false);
		order.setPayment(paymentMood);
		
		int totalCost=0;
		List<Cart> cartItems = cartService.cartItemByUserName(userName);
		for(Cart cart : cartItems) {
			System.out.println("this is inside for loop");
			OrderItem orderItem = new OrderItem();
			orderItem.setOrderId(order.getId());
			orderItem.setProTitle(cart.getProductTitle());
			orderItem.setProQuantity(cart.getQuantity());
			orderItem.setPrice(cart.getPrice()*cart.getQuantity());
			
			orderItemService.addOrderItem(orderItem);
			totalCost+=cart.getQuantity() * cart.getPrice();
			cartService.deleteCartById(cart.getId());
			
		}
		if(totalCost<200) totalCost+=20;
		order.setTotalCost(totalCost);
		
		if(paymentMood.equalsIgnoreCase("Online")){
			orderService.addOrder(order);
			TreeMap<String, String> parameter= new TreeMap<>();
			parameter.put("MID" , env.getProperty("paytm.merchantId"));
			parameter.put("CHANNEL_ID" , "WEB");
			parameter.put("INDUSTRY_TYPE_ID", "Retail");
			parameter.put("WEBSITE", "DEFAULT");
			parameter.put("MOBILE_NO", env.getProperty("paytm.mobile"));
			parameter.put("EMAIL_ID", env.getProperty("paytm.email"));
			System.out.println("good things happen you are in the online mode0");
			parameter.put("ORDER_ID", order.getId()+"");
			parameter.put("TXN_AMOUNT", order.getTotalCost()+"");
			System.out.println("good things happen you are in the online mode1");
			parameter.put("CUST_ID", userName);
			System.out.println("good things happen you are in the online mode2");
			String checkSum = getCheckSum(parameter);
			System.out.println("good things happen you are in the online mode3");
			parameter.put("CHECKSUMHASH", checkSum);
			System.out.println("good things happen you are in the online mode4");
			model.addAllAttributes(parameter);
			
			return "redirect:https://securegw-stage.paytm.in/order/process";
		}
		else {
			order.setOrderd(true);
			orderService.addOrder(order);
			model.addAttribute("categories", categoryService.getAllCategory());
			return "redirect:/home";
		}
		
	}
	
	@PostMapping("/pgresponse")
	public String getResponseRedirect(HttpServletRequest request, Model model) {

		Map<String, String[]> mapData = request.getParameterMap();
		TreeMap<String, String> parameters = new TreeMap<String, String>();
		String paytmChecksum = "";
		for (Entry<String, String[]> requestParamsEntry : mapData.entrySet()) {
			if ("CHECKSUMHASH".equalsIgnoreCase(requestParamsEntry.getKey())){
				paytmChecksum = requestParamsEntry.getValue()[0];
			} else {
				parameters.put(requestParamsEntry.getKey(), requestParamsEntry.getValue()[0]);
			}
		}
		String result;

		boolean isValideChecksum = false;
		System.out.println("RESULT : "+parameters.toString());
		try {
			isValideChecksum = validateCheckSum(parameters, paytmChecksum);
			if (isValideChecksum && parameters.containsKey("RESPCODE")) {
				if (parameters.get("RESPCODE").equals("01")) {
					result = "Payment Successful";
				} else {
					result = "Payment Failed";
				}
			} else {
				result = "Checksum mismatched";
			}
		} catch (Exception e) {
			result = e.toString();
		}
		model.addAttribute("result",result);
		parameters.remove("CHECKSUMHASH");
		model.addAttribute("parameters",parameters);
		return "redirect:/home";
	}

	private boolean validateCheckSum(TreeMap<String, String> parameters, String paytmChecksum) throws Exception {
		return PaytmChecksum.verifySignature(parameters,
				env.getProperty("paytm.merchantKey"), paytmChecksum);
	}

	private String getCheckSum(TreeMap<String, String> parameter) throws Exception {
		// TODO Auto-generated method stub
		return PaytmChecksum.generateSignature(parameter, env.getProperty("paytm.merchantId"));
	}
}


