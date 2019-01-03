package de.kb.bootstrap.components.websocket_messages;

public class Image {
	private final String id;
	private final String fileName;
	public Image(String id, String fileName) {
		super();
		this.id = id;
		this.fileName = fileName;
	}
	
	public String getId() {
		return this.id;
	}
	public String getFileName() {
		return this.fileName;
	}
}