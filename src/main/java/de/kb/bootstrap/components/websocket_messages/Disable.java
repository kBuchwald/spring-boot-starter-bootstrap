package de.kb.bootstrap.components.websocket_messages;

public class Disable {
	private final boolean disabled;
	private final String componentId;

	public Disable(boolean disabled, String componentId) {
		super();
		this.disabled = disabled;
		this.componentId = componentId;
	}

	public boolean isDisabled() {
		return this.disabled;
	}
	
	public String getComponentId() {
		return this.componentId;
	}
}