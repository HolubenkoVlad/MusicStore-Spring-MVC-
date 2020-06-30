package ua.guitarshop.utils;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ua.guitarshop.objects.*;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {
	@PersistenceContext
    private EntityManager entityManager;
	
	public void create(Item item) {
    	entityManager.persist(item);
    	entityManager.flush();
    }
	
	public void update(Item item) {
		entityManager.merge(item);
		entityManager.flush();
	}
    
    @SuppressWarnings("unchecked")
	public List<Item> findAll() {
	    Query query = entityManager.createQuery("SELECT e FROM Item e");
	    return (List<Item>) query.getResultList();
    }

    public Item findById(int id){
    	Query query = entityManager.createQuery("SELECT e FROM Item e");
    	return entityManager.find(Item.class, id);
    }
    
    public List<Item> findmintomax(float min, float max){
    	Query query = entityManager.createQuery("SELECT e FROM Item e where e.price between :min and :max");
    	query.setParameter("min", min);
    	query.setParameter("max", max);
    	return (List<Item>)query.getResultList();
    }
}
