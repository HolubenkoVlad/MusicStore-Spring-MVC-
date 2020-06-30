package ua.guitarshop.servlets;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ua.guitarshop.objects.Item;
import ua.guitarshop.objects.JsonResponse;
import ua.guitarshop.services.ItemService;

@RestController
@RequestMapping(value = "/api")
public class AdminController {
	
	@Autowired
	private ItemService itemService;

	@RequestMapping(value="/items.do", method=RequestMethod.GET)
	 public List<Item> getItem() {
        return itemService.findAll();
    }
	
	@RequestMapping(value="/items/{itemId}.do", method=RequestMethod.GET)
	 public Item getItemById(@PathVariable int itemId) {
       return itemService.getById(itemId);
   }
	
	@RequestMapping(value="/items.do", method=RequestMethod.POST)
	 public JsonResponse addItem(@RequestBody Item item) {
		System.out.println(item.getId());
		itemService.add(item);
		return new JsonResponse(true, "Added!");
   }
	
	@RequestMapping(value="/items/{itemId}.do", method=RequestMethod.PUT)
	 public JsonResponse updateItem(@RequestBody Item item) {
		System.out.println(item.getName());
		itemService.update(item);
		return new JsonResponse(true, "Updated!");
   }
	
	@RequestMapping(value="/items/{from}/{to}.do", method=RequestMethod.GET)
	 public List<Item> getFilteredItems(@RequestParam float min, @RequestParam float max) {
		return itemService.findLim(min, max);		
  }
	
}
