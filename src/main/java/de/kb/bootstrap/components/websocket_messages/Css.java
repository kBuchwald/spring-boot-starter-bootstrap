package de.kb.bootstrap.components.websocket_messages;

public class Css {
	private final String cssClass; 
	private final String componentId;

	public Css(String cssClass, String componentId) {
		this.cssClass = cssClass;
		this.componentId = componentId;
	}

	public String getCssClass() {
		return this.cssClass;
	}

	public String getComponentId() {
		return this.componentId;
	}
}