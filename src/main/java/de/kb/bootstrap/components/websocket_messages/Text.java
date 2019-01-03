package de.kb.bootstrap.components.websocket_messages;

public class Text {
	
	private final String text; 
	private final String componentId;

	public Text(String text, String componentId) {
		this.text = text;
		this.componentId = componentId;
	}

	public String getText() {
		return this.text;
	}

	public String getComponentId() {
		return this.componentId;
	}
}