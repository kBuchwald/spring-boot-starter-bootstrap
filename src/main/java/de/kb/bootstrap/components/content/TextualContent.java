package de.kb.bootstrap.components.content;

public interface TextualContent extends Content{
	public enum Type{
		PARAGRAPH,SPAN,PLAIN;
	}
	
	<T extends TextualContentVisitor> T accept(T visitor);
}