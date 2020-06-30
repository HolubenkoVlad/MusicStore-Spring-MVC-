package ua.guitarshop.objects;


public class JsonResponse {

    private boolean status;
	
    private String message;

    public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
    
    public JsonResponse(){
    	
    }
    
   public JsonResponse(boolean status, String message){
    	this.status=status;
    	this.message=message;
    }

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
