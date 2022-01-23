package com.teja.dao;

import java.util.List;

import javax.persistence.Query;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.teja.entity.OrderItems;
import com.teja.entity.Orders;
import com.teja.entity.User;

@Repository
public class OrderDao {

	@Autowired
	SessionFactory factory;
	
	@Transactional
	public Object addOrder(List<OrderItems> orderItemsList,Orders order,User user) {
		Session session = factory.getCurrentSession();
		session.saveOrUpdate(order);
//		session.saveOrUpdate(user);
		for(OrderItems orderItem:orderItemsList) {
			session.saveOrUpdate(orderItem);
		}
		return order;
	}
	
	@Transactional
	public Object getDetailOrder(String id) {
		Session session = factory.getCurrentSession();
		String queryString = String.format("select * from orderitems where orderId = '%s'", id);
		Query query = session.createNativeQuery(queryString, OrderItems.class);
		List<OrderItems> orderItems = (List<OrderItems>) query.getResultList();
		return orderItems;
	}
}
