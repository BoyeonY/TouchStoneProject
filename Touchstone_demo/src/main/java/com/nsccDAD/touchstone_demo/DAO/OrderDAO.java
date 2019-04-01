package com.nsccDAD.touchstone_demo.DAO;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nsccDAD.touchstone_demo.entity.Order;

@Repository
public class OrderDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	public Order insertOrder(Order order) {
		Session session = sessionFactory.getCurrentSession();		
		session.save(order);		
		return order;
	}
	
	public List<Order> getOrders(){
		Session session = sessionFactory.getCurrentSession();
		
		Query<Order> getOrders = session.createQuery("from Order where order_process != 'Done'");
		List<Order> orders = getOrders.getResultList();
		
		return orders;
	}
	public List<Order> getOrders(String order_process){
		Session session = sessionFactory.getCurrentSession();
		
		Query<Order> getOrders = session.createQuery("from Order where order_process = '" +order_process+"'");
		List<Order> orders = getOrders.getResultList();
		
		return orders;
	}
	public Order getOrder(int order_id) {
		Session session = sessionFactory.getCurrentSession();
		return session.get(Order.class, new Integer(order_id));
	}
	public void updateOrder(Order order) {
		Session session = sessionFactory.getCurrentSession();
		session.update(order);
	}
}
