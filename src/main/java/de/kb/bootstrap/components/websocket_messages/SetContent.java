package de.kb.bootstrap.components.websocket_messages;

public class SetContent {
	private final String parentId;

	public SetContent(String parentId) {
		super();
		this.parentId = parentId;
	}

	public String getParentId() {
		return this.parentId;
	}
}