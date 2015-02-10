package shared;

import java.io.Serializable;

public class Message<E extends Serializable> implements Serializable {
	private static final long serialVersionUID = 732925911515696139L;
	private String sender;
	private E content;
	
	public Message(E content, String sender) {
		this.sender = sender;
		this.content = content;
	}
}
