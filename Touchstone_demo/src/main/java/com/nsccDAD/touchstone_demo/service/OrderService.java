package com.nsccDAD.touchstone_demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nsccDAD.touchstone_demo.DAO.OrderDAO;
import com.nsccDAD.touchstone_demo.entity.Order;

@Service
public class OrderService {
	
	@Autowired
	private OrderDAO orderDAO;
	
	@Transactional
	public String insertOrder(String cusName) {
		Order order = orderDAO.insertOrder(new Order(cusName));
		String returnMsg = "Order completed: order# " + order.getOrder_id();
		return returnMsg;
	}
	
	@Transactional
	public List<Order> getOrders(GrantedAuthority role){
		if(role.getAuthority().equals("Boss") || role.getAuthority().equals("Admin")) {
			// show all un-finished orders
			return orderDAO.getOrders();
		}else if (role.getAuthority().equals("Template")) {
			// show all orders that are in Template step
			return orderDAO.getOrders("Template");
		}else if (role.getAuthority().equals("Installation")) {
			// show all ordders that are in Installation step
			return orderDAO.getOrders("Installation");
		}else {
			return null;
		}
	}
	
	@Transactional
	public void updateOrder(int order_id) {
		Order order = orderDAO.getOrder(order_id);
		String curProcess = order.getOrder_process();
		if(curProcess.equals("Order")) {
			order.setOrder_process("Approval");
			
		}else if(curProcess.equals("Approval")) {
			order.setOrder_process("Template");
			
		}else if(curProcess.equals("Template")) {
			order.setOrder_process("Installation");
			
		}else if(curProcess.equals("Installation")) {
			order.setOrder_process("Done");
			
		}
		orderDAO.updateOrder(order);
	}
}
