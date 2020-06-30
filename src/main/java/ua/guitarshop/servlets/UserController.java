package ua.guitarshop.servlets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import ua.guitarshop.objects.JsonResponse;
import ua.guitarshop.objects.RegistrationForm;
import ua.guitarshop.objects.User;
import ua.guitarshop.services.UserService;

@Controller
@SessionAttributes(value = {"login", "Cart"})
public class UserController {
	
	@Autowired
    private UserService userService;

	@RequestMapping(value="/login.do", method=RequestMethod.GET)
	public String loginGet(){
		return "login";
	}
	
		
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public @ResponseBody JsonResponse login(@RequestParam String login, 
											@RequestParam String password, Model model){
		User user=userService.isLoginUsed(login);
        if (user.getPassword().equals(password)||user.equals(null)) {
        	System.out.println("password = password");
        	   System.out.println(user.getLogin());
        	model.addAttribute("login", user);
        	return new JsonResponse(true,"privateroom");
        } else {
        	return new JsonResponse(false,"Wrong login or password");
        }
	}
	
	@RequestMapping(value="/logout.do", method=RequestMethod.POST)
	public String logout(@RequestHeader("Referer") String referer, SessionStatus sessionStatus, 
						   @ModelAttribute("login") User user){
		sessionStatus.setComplete();
    	return redirectBack(referer);
	}	
	
	@RequestMapping(value="/register.do", method=RequestMethod.GET)
	public String registerGet(RegistrationForm regForm, Model model){
		model.addAttribute("regForm", new RegistrationForm());
		return "register";
	}	
	
	@RequestMapping(value="/register.do", method=RequestMethod.POST)
	public @ResponseBody JsonResponse registerPost(Model model, @ModelAttribute RegistrationForm regForm){
		String answer=CheckRegForm(regForm);
		if(answer.contains("ok")) {
	        userService.add(RegFormToUser(regForm));
	        return new JsonResponse(true,"login");
		}
		else return new JsonResponse(false,answer);
	}	
	
	public String CheckRegForm(RegistrationForm regForm) {
		if(regForm.getSurname()=="") return "Enter surname";
		else if(regForm.getEmail()=="") return "Enter email";
		else if(regForm.getLogin()=="") return "Enter login";
		else if(regForm.getPassword()=="") return "Enter password";
		else return "ok";
	}
	
    private User RegFormToUser(RegistrationForm form) {
        User user = new User();
        user.setSurname(form.getSurname());
        user.setLogin(form.getLogin());
        user.setPassword(form.getPassword());
        user.setEmail(form.getEmail());
        return user;
    }
    
	private String redirectBack(String referer){
        if (referer != null && referer.contains("/privateroom")) {
        	return "redirect:list.do";
        } else {
        	return "redirect:"+referer;
        }
	}
	
}
