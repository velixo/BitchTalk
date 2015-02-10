package shared;

import java.io.Serializable;

public class Message<E extends Serializable> implements Serializable {
	public static final String USERLIST = "userlist";
	public static final String COMMAND = "command";
	public static final String TEXT = "text";
	
	private static final long serialVersionUID = 732925911515696139L;
	private String messageType;
	private String sender;
	private E content;
	
	public Message(String sender, String messageType, E content) {
		this.sender = sender;
		this.messageType = messageType;
		this.content = content;
	}
	
	public String getType() {
		return messageType;
	}
	
	public E getContent() {
		return content;
	}
}
