package ua.guitarshop.objects;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="orderitems")
public class CartItem implements Serializable 
{
	private static final long serialVersionUID = -7645707326388491379L;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_item")
	private Item item;
	
	@Id
	@ManyToOne
	@JoinColumn(name="id_order")
	private OrderUser order;
	
	private int count=0;
	@Column(name = "price")
	private float totalprice=0;
	
	public CartItem() {
		
	}
	
	public CartItem(Item item, int quentity, float totalprice) {
		this.item=item;
		this.totalprice=totalprice;
		this.count=quentity;
	}


	public int getCount() {
		return count;
	}

	public void setCount(int quentity) {
		count += quentity;
	}

	public float getTotalprice() {
		return totalprice;
	}

	public void setTotalprice(float totalprice) {
		this.totalprice += totalprice;
	}
	
	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}
	
	public OrderUser getOrder() {
		return order;
	}

	public void setOrder(OrderUser order) {
		this.order = order;
	}
}
