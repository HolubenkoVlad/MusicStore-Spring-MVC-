package ua.guitarshop.utils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import ua.guitarshop.objects.*;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
	@PersistenceContext
    private EntityManager entityManager;

    public void create(User user) {
    	entityManager.persist(user);
    	entityManager.flush();
    }

    public User findById(String login)  {
    	   System.out.println("findById");
    	   Query query = entityManager.createQuery("SELECT e FROM User e where e.login=:login").setParameter("login", login);
   	    return (User) query.getSingleResult();
    }
}
