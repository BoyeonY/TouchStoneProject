package com.nsccDAD.touchstone_demo.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.nsccDAD.touchstone_demo.entity.Order;
import com.nsccDAD.touchstone_demo.service.OrderService;

@Controller
public class OrdersController {
	@Autowired
	OrderService orderService;
	@GetMapping("/")
	public ModelAndView orders(HttpServletRequest req) {
		ModelAndView main = new ModelAndView("main");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		GrantedAuthority role = new ArrayList<GrantedAuthority>(auth.getAuthorities()).get(0);
		List<Order> orders = orderService.getOrders(role);
		
		main.addObject("orders", orders);
		main.addObject("user_id", req.getUserPrincipal().getName());
		main.addObject("user_role", role);
		return main;
	}
	
	@GetMapping("/makeAnOrder")
	public String makeAnOrder() {
		return "makeAnOrder";
	}
	
	@PostMapping("/makeAnOrder")
	public ModelAndView insertAnOrder(HttpServletRequest req){
		ModelAndView orderCompleted = new ModelAndView("orderCompleted");
		String cusName = req.getParameter("cus_name");
		String returnMsg = orderService.insertOrder(cusName);
		orderCompleted.addObject("returnMsg", returnMsg);
		return orderCompleted;
	}
	
	@PostMapping("/updateOrder")
	public ModelAndView updateOrder(HttpServletRequest req) {
		int order_id = Integer.parseInt(req.getParameter("order_id"));
		orderService.updateOrder(order_id);
		return new ModelAndView("redirect:/");
	}
}
