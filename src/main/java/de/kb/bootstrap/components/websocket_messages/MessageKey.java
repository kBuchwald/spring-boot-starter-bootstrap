package de.kb.bootstrap.components.websocket_messages;

public class MessageKey {
	private final String msgKey;
	private final String componentId;

	public MessageKey(String msgKey, String componentId) {
		this.msgKey = msgKey;
		this.componentId = componentId;
	}

	public String getMsgKey() {
		return this.msgKey;
	}

	public String getComponentId() {
		return this.componentId;
	}
}