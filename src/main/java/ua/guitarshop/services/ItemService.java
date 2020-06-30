package ua.guitarshop.services;

import ua.guitarshop.objects.Item;
import ua.guitarshop.objects.User;
import ua.guitarshop.utils.ItemRepository;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class ItemService {
	@Autowired
	   private ItemRepository itemRepository;
	
	   public void add(final Item item) {
          itemRepository.create(item);
       }
	   
	   public void update(final Item item) {
		   itemRepository.update(item);
	   }

	   public List<Item> findAll() {
	      return itemRepository.findAll();
	   }
	   
	   public List<Item> findLim(float min, float max) {
		      return itemRepository.findmintomax(min, max);
		   }

	   public Item getById(final int id){
	      return itemRepository.findById(id);
	   }

}

