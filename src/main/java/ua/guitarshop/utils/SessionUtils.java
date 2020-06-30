package ua.guitarshop.utils;

import javax.servlet.http.HttpSession;

import ua.guitarshop.objects.*;


public class SessionUtils {	
	public static void storeTotalPrice(HttpSession session, Float price) {
		System.out.println("totalPrice in SessionUtils -  " +price);
		session.setAttribute("totalprice", price);
	}
	
	public static float getTotalPrice(HttpSession session) {
		float price=0;
		System.out.println(session);
		if(session.getAttribute("totalprice")==null) {
			System.out.println("Print NULL");
			session.setAttribute("totalprice", (float)0);
		}
		else {
			System.out.println("Print Tots");
			price=(Float)session.getAttribute("totalprice");
		}
		return price;
	}
    
 
    public static void storeLoginedUser(HttpSession session, User loginedUser) {
        session.setAttribute("login", loginedUser);
    }
 
    public static User getLoginedUser(HttpSession session) {
        User loginedUser = (User) session.getAttribute("login");
        return loginedUser;
    }
    
    public static Cart getCart(HttpSession session){
        Cart cart;
        if (session.getAttribute("Cart") == null) {
            cart = new Cart();
            session.setAttribute("Cart", cart);
        } else {
            cart = (Cart)session.getAttribute("Cart");
        }
        return cart;
    }

    public static void clearCart(HttpSession session){
        session.setAttribute("Cart", new Cart());
    }
}
