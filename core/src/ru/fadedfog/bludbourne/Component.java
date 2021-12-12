package ru.fadedfog.bludbourne;

public interface Component {

	public static final String MESSAGE_TOKEN = "";
	
	public static enum MESSAGE {
	}

	void receiveMessage(String fullMessage);

	void dispose();
}
