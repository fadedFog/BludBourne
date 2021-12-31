package ru.fadedfog.bludbourne;

public class ConversationChoice {
	private String soureId;
	private String destinationId;
	private String choicePhrase;
	private ConversationCommandEvent conversationCommandEvent;
	
	public ConversationChoice() {
	}

	public String getSoureId() {
		return soureId;
	}

	public void setSoureId(String soureId) {
		this.soureId = soureId;
	}

	public String getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(String destinationId) {
		this.destinationId = destinationId;
	}

	public String getChoicePhrase() {
		return choicePhrase;
	}

	public void setChoicePhrase(String choicePhrase) {
		this.choicePhrase = choicePhrase;
	}

	public ConversationCommandEvent getConversationCommandEvent() {
		return conversationCommandEvent;
	}

	public void setConversationCommandEvent(ConversationCommandEvent conversationCommandEvent) {
		this.conversationCommandEvent = conversationCommandEvent;
	}

	// TODO Implement methods: toString
	
}
