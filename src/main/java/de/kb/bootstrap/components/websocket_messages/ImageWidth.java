package de.kb.bootstrap.components.websocket_messages;

public class ImageWidth {
	private final String id;
	private final int width;
	public ImageWidth(String id, int width) {
		super();
		this.id = id;
		this.width = width;
	}
	
	public String getId() {
		return this.id;
	}
	public int getWidth() {
		return this.width;
	}
}