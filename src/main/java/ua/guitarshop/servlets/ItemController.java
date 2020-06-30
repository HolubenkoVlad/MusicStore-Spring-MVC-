package ua.guitarshop.servlets;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import ua.guitarshop.objects.Cart;
import ua.guitarshop.objects.Item;
import ua.guitarshop.objects.JsonResponse;
import ua.guitarshop.objects.OrderForm;
import ua.guitarshop.objects.CartItem;
import ua.guitarshop.objects.OrderUser;
import ua.guitarshop.objects.User;
import ua.guitarshop.services.ItemService;
import ua.guitarshop.services.OrderService;
import ua.guitarshop.utils.SessionUtils;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

@Controller
@SessionAttributes(value={"login","order"})
public class ItemController {
	@Autowired
	private ItemService itemService;
	
	@Autowired
	private OrderService orderService;
	
	@RequestMapping(method=RequestMethod.GET)
		public String showCatalog(Model model, Cart cart) {
		List<Item> list=itemService.findAll();
		model.addAttribute("product",list);
		return "catalog";
	}
	
	@RequestMapping(value="/privateroom.do", method=RequestMethod.GET)
	public String showCart(Cart cart){
        return "privateroom";
	}
	
	@RequestMapping(value="/add_to_cart.do", method=RequestMethod.POST)
	public @ResponseBody String addToCart(@RequestParam String id, HttpSession session){
		Item product=itemService.getById(Integer.parseInt(id));
		CartItem cartItem=new CartItem(product,1,product.getPrice());
        Cart cart = SessionUtils.getCart(session);
        CartItem temp=cart.findbyId(Integer.parseInt(id));
        if(temp==null) {
        	cart.add(cartItem);
        }
        else {
        	temp.setCount(1);
        	temp.setTotalprice(product.getPrice());
        }        
        float p=SessionUtils.getTotalPrice(session)+product.getPrice();
        SessionUtils.storeTotalPrice(session, p);
        return "ok";
	}
	
	@RequestMapping(value="/remove_from_cart.do", method=RequestMethod.GET)
	public String removeFromCart(@RequestParam Integer position, HttpSession session){
		Cart cart = SessionUtils.getCart(session);
		CartItem temp= cart.getId(position);
		SessionUtils.storeTotalPrice(session, SessionUtils.getTotalPrice(session)-temp.getTotalprice());
	    cart.remove(position);     
		return "redirect:privateroom.do";
	}
	
	@RequestMapping(value="/erase_cart.do", method=RequestMethod.GET)
	public String eraseCart(HttpSession session){
		SessionUtils.clearCart(session);
		session.setAttribute("totalprice", (float)0);
		return "redirect:privateroom.do";
	}
	
	@RequestMapping(value="/order.do", method=RequestMethod.GET)
	public String getorder(@ModelAttribute OrderUser orObj, Model model){
		model.addAttribute("order", orObj);
		return "order";
	}
	
	
	@RequestMapping(value="/addorder.do", method=RequestMethod.POST)
	public @ResponseBody JsonResponse order(@RequestBody OrderForm orderForm,
			@ModelAttribute("login") User user, Model model,HttpSession session) {
		Cart cart=SessionUtils.getCart(session);
		if(cart.getCount()==0) {
			return new JsonResponse(false, "Empty cart");
		}
		if(orderForm.getAddress()=="" || orderForm.getCard()=="") {
			return new JsonResponse(false, "No address or card is entered");
		}	
		OrderUser order=new OrderUser(user.getId(),orderForm.getCard(),orderForm.getAddress(),cart.getItems());
		order=orderService.add(order, user.getLogin());
		session.setAttribute("orderob", order);
		return new JsonResponse(true, "order");
	}
	
	
	
	
}
