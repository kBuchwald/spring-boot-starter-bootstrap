package de.kb.bootstrap.components.websocket_messages;

public class ImageHeight {
	private final String id;
	private final int height;
	public ImageHeight(String id, int height) {
		super();
		this.id = id;
		this.height = height;
	}
	
	public String getId() {
		return this.id;
	}
	public int getHeight() {
		return this.height;
	}
}