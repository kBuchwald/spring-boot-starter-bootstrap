package de.kb.bootstrap.components.websocket_messages;

public class AnchorLink {
	private final String id;
	private final String link;

	public AnchorLink(String id, String link) {
		super();
		this.id = id;
		this.link = link;
	}

	public String getId() {
		return this.id;
	}

	public String getLink() {
		return this.link;
	}
}