package ua.guitarshop.utils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import ua.guitarshop.objects.OrderUser;

@Repository
public class OrderRepository {
	@PersistenceContext
    private EntityManager entityManager;

	public OrderUser create(OrderUser order){
		entityManager.persist(order);
		entityManager.flush();
		return order;
	}
}
