package ua.guitarshop.services;

import ua.guitarshop.objects.User;
import ua.guitarshop.utils.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class UserService {
	@Autowired
    UserRepository userRepository;

    public void add(final User user) {
        userRepository.create(user);
    }

    public User isLoginUsed(final String login) {
    	   System.out.println("isLoginUsed");
        return userRepository.findById(login);
    }

    public boolean login(final String login, final String password) {
        User user = userRepository.findById(login);
        return user != null && user.getPassword() != null && user.getPassword().equals(password);
    }
}

