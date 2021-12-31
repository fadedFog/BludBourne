package ru.fadedfog.bludbourne;

public interface ConversationGraphObserver {
	void onNotify(ConversationGraph graph, ConversationCommandEent event);
}
