package de.kb.bootstrap.components.websocket_messages;

public class Remove {
	private final String componentId;
	
	public Remove(String componentId) {
		this.componentId = componentId;
	}
	
	public String getComponentId() {
		return this.componentId;
	}
}