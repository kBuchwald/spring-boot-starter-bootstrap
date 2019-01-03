package de.kb.bootstrap.components.content;

import java.util.Collections;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.kb.bootstrap.components.AbstractBootstrapComponent;
import de.kb.bootstrap.components.Style;
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MessageKeyContent extends AbstractBootstrapComponent<MessageKeyContent> implements TextualContent{
	private String msgKey;
	private Type type;

	@PostConstruct
	public void init() {
		super.init();
		this.type = Type.SPAN;
	}

	public MessageKeyContent setMsgKey(String msgKey) {
		this.msgKey = msgKey;
		return this;
	}

	public MessageKeyContent setType(Type type) {
		this.type = type;
		return this;
	}

	public String getMsgKey() {
		return this.msgKey;
	}

	@Override
	public Set<Style> getBaseStytes() {
		return Collections.emptySet();
	}
	
	public Type getType() {
		return type;
	}
	
	public <T extends TextualContentVisitor> T accept(T visitor) {
		visitor.visit(this);
		return visitor;
	}
}