package de.kb.bootstrap.components.content;

public interface TextualContentVisitor {
	
	void visit(TextContent visitee);
	void visit(MessageKeyContent visitee);
}