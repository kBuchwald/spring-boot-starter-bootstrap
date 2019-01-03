package de.kb.bootstrap.components.websocket_messages;

public class AddContent {
	private final String parentId;
	private final String contentId;
	private final String afterId;
	private final boolean asFirst;

	public AddContent(String parentId, String contentId) {
		super();
		this.parentId = parentId;
		this.contentId = contentId;
		this.afterId = null;
		this.asFirst = false;
	}
	
	public AddContent(String parentId, String contentId, String afterId) {
		super();
		this.parentId = parentId;
		this.contentId = contentId;
		this.afterId = afterId;
		this.asFirst = false;
	}
	
	public AddContent(String parentId, String contentId, boolean asFirst) {
		super();
		this.parentId = parentId;
		this.contentId = contentId;
		this.afterId = null;
		this.asFirst = asFirst;
	}

	public String getParentId() {
		return this.parentId;
	}

	public String getContentId() {
		return this.contentId;
	}

	public String getAfterId() {
		return this.afterId;
	}

	public boolean isAsFirst() {
		return this.asFirst;
	}
}