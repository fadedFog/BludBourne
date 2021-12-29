package ru.fadedfog.bludbourne;

import java.util.Hashtable;

public class ConversationGraph extends ConversationGraphSubject {
	private Hashtable<String, Conversation> conversations;
	private Hashtable<String, ConversationChoice> associatedChoies;
	private String currentConversationID = null;
	
	public ConversationGraph() {
	}
	
	public ConversationGraph(Hashtable<String, Conversation> conversations, String rootID) {
		this.conversations = conversations;
	}
	
	public void setConversations(Hashtable<String, Conversation> conversations) {
		this.conversations = conversations;
	}
	
	public ConversationChoice[] getCurrentChoise() {
		return null;
	}
	
	public String getCurrentConverstationID() {
		return currentConversationID;
	}
	
	public void setCurrentConverstationID(String ID) {
		this.currentConversationID = ID;
	}
	
	public boolean isValid(String conversationID) {
		return false;
	}
	
	public boolean isReachable(String sourceID, String sindID) {
		return false;
	}
	
	public Conversation getConversationByID(String ID) {
		return null;
	}
	
	public String diplayCurrentConversation() {
		return "";
	}
	
	public void addChoice(ConversationChoice conversationChoice) {
		
	}
	
	// TODO implement methods: toString 
	
	public String toJson() {
		return "";
	}
	
}
