package ua.guitarshop.objects;

import java.util.ArrayList;
import java.util.List;


public class Cart {
	public List<CartItem> list=new ArrayList<>();
	
	public void add(CartItem item) {
        list.add(item);
    }

    public List<CartItem> getItems(){
        return new ArrayList<>(list);
    }
    
    public CartItem getId(int i) {
    	return list.get(i);
    }

    public int getCount() {
        return list.size();
    }

    public void remove(int pos) {
        list.remove(pos);
    }

	public Float removebyId(int parseInt) {
		float temp;
		for(int i=0;i<list.size();i++) {
    		if(list.get(i).getItem().getId()==parseInt) {
    			System.out.println("Remoted id "+i);
    			temp=list.get(i).getItem().getPrice();
    			list.remove(i);
    			return temp;
    		}
    	}
		return null;
	}
	
	public CartItem findbyId(int id) {
		for(int i=0;i<list.size();i++) {
    		if(list.get(i).getItem().getId()==id) {
    			return  list.get(i);   			
    		}
    	}
		return null;
	}

}
