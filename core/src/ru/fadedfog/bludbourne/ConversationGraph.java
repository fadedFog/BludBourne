package ru.fadedfog.bludbourne;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Set;

import com.badlogic.gdx.utils.Json;

public class ConversationGraph extends ConversationGraphSubject {
	private Hashtable<String, Conversation> conversations;
	private Hashtable<String, ArrayList<ConversationChoice>> associatedChoies;
	private String currentConversationID = null;
	
	public ConversationGraph() {
	}
	
	public ConversationGraph(Hashtable<String, Conversation> conversations, String rootID) {
		setConversations(conversations);
		setCurrentConverstationID(rootID);
	}
	
	public void setConversations(Hashtable<String, Conversation> conversations) {
		if (conversations.size() < 0) {
			throw new IllegalArgumentException("Can't have a negative amount of conversations");
		}
		
		this.conversations = conversations;
		this.associatedChoies = new Hashtable<String, ArrayList<ConversationChoice>>(conversations.size());
		
		for (Conversation conversation: conversations.values()) {
			associatedChoies.put(conversation.getId(), 
					new ArrayList<ConversationChoice>());
		}
	}
	
	public ArrayList<ConversationChoice> getCurrentChoise() {
		return associatedChoies.get(currentConversationID);
	}
	
	public String getCurrentConverstationID() {
		return currentConversationID;
	}
	
	public void setCurrentConverstationID(String ID) {
		this.currentConversationID = ID;
	}
	
	public boolean isValid(String conversationID) {
		Conversation conversation = conversations.get(conversationID);
		return conversation == null;
	}
	
	public boolean isReachable(String sourceID, String sindID) {
		if (!isValid(sourceID) || !isValid(sindID)) {
			return false;
		}
		if (conversations.get(sourceID) == null) {
			return false;
		}
		
		ArrayList<ConversationChoice> list = associatedChoies.get(sourceID);
		if (list == null) {
			return false;
		}
		
		for (ConversationChoice choice: list) {
			if (choice.getDestinationId().equalsIgnoreCase(sindID)) {
				return true;
			}
		}
		return false;
	}
	
	public Conversation getConversationByID(String id) {
		if (!isValid(id)) {
			System.out.println("Id " + id + " is not valid!");
			return null;
		}
		return conversations.get(id);
	}
	
	public String diplayCurrentConversation() {
		return conversations.get(currentConversationID).getDialog();
	}
	
	public void setCurrentConversation(String id) {
		Conversation conversation = getConversationByID(id);
		if (conversation == null) {
			return;
		}
		
		if (currentConversationID == null 
				|| currentConversationID.equalsIgnoreCase(id) 
				|| isReachable(currentConversationID, id)) {
			currentConversationID = id;
		} else {
			System.out.println("new conversation node [" + id 
					+ "] is not reachable from current node [" + currentConversationID + "]");
		}
	}
	
	public void addChoice(ConversationChoice conversationChoice) {
		ArrayList<ConversationChoice> list = associatedChoies.get(conversationChoice.getSoureId());
		if (list == null) {
			return;
		}
		
		list.add(conversationChoice);
	}
	
	// TODO implement methods: toString 
	public String toString() {
		StringBuilder outputString = new StringBuilder();
		int numberTotalChoices = 0;
		
		Set<String> keys = associatedChoies.keySet();
		for (String id: keys) {
			outputString.append(String.format("[%s]: ", id));
			
			for (ConversationChoice choice: associatedChoies.get(id)) {
				numberTotalChoices++;
				outputString.append(String.format("%s ", choice.getDestinationId()));
			}
			outputString.append(System.getProperty("line.separator"));
		}
		
		outputString.append(String.format("Number conversations: %id", conversations.size()));
		outputString.append(String.format(", Number of choices: %d", numberTotalChoices));
		outputString.append(System.getProperty("line.separator"));
		
		return outputString.toString();
	}
	
	public String toJson() {
		Json json = new Json();
		return json.prettyPrint(this);
	}
	
}
