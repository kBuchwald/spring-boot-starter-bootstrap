package de.kb.bootstrap.reesourcces;

import org.springframework.http.MediaType;

public class ImageRessource {
	private final String id;
	private final String fileName;
	private final MediaType mediaType;
	private final byte[] data;
	public ImageRessource(String id,String fileName, MediaType mediaType, byte[] data) {
		super();
		this.id =id;
		this.fileName = fileName;
		this.mediaType = mediaType;
		this.data = data;
	}

	public String getFileName() {
		return this.fileName;
	}
	public MediaType getMediaType() {
		return this.mediaType;
	}
	public byte[] getData() {
		return this.data;
	}
	public String getId() {
		return this.id;
	}
}