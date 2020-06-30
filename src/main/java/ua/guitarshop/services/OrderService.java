package ua.guitarshop.services;

import ua.guitarshop.objects.CartItem;
import ua.guitarshop.objects.OrderUser;
import ua.guitarshop.utils.OrderRepository;

import java.io.Serializable;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class OrderService {
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
    private JmsTemplate jmsTemplate;
	@Autowired
	private Destination orderDestination;
	
	public OrderUser add(final OrderUser src, String userLogin) {
		for (CartItem oi : src.getList()) {
			oi.setOrder(src);
		}
		OrderUser order= orderRepository.create(src);
		sendOrder(order, userLogin);
		return order;
	}
	
	private void sendOrder(OrderUser order, String userLogin) {
		 this.jmsTemplate.send(orderDestination, new MessageCreator() {
		      @Override
		      public Message createMessage(Session session) throws JMSException {
		        //MapMessage message = session.createMapMessage();
		        ObjectMessage message =session.createObjectMessage();
		        System.out.println("orderId "+order.getId());
		        message.setIntProperty("orderId", order.getId());
		        System.out.println("userLogin "+userLogin);
		        message.setStringProperty("userLogin", userLogin);
		        message.setObjectProperty("CartItems", order.getListItems());
		        System.out.println("userLogin "+order.getTotal());
		        message.setFloatProperty("total", order.getTotal());
		        return message;
		      }
		    });		
	}
}
