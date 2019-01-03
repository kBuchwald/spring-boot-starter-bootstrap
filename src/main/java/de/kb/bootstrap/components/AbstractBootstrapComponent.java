package de.kb.bootstrap.components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import de.kb.bootstrap.components.websocket_messages.Css;
import de.kb.bootstrap.components.websocket_messages.Remove;

public abstract class AbstractBootstrapComponent<T extends BootstrapComponent> implements BootstrapComponent {
	
	private String id;
	private boolean rendered = false;
	private List<ComponentRemover>  remover = new ArrayList<>();
	private BootstrapComponent parent;
	private Set<Style> styles = new HashSet<>();
	
	@Autowired
	private IdGenerator idGenerator;
	
	@Autowired
	private PageCache pageCache;
	
	@Autowired
	protected SimpMessagingTemplate webSocket;
	
	@Override
	public String getId() {
		return id;
	}
	
	protected void init() {
		id = idGenerator.nextId(this);
	}
	
	public void setRendered() {
		this.rendered = true;
	}
	
	public boolean isRendered() {
		return rendered;
	}
	
	public void addRemover(ComponentRemover remover) {
		this.remover.add(remover);
	}
	
	public void remove() {
		remover.forEach(ComponentRemover::remove);
		if (isRendered())
			this.webSocket.convertAndSend("/topic/content/remove", new Remove(getId()));
	}
	
	public void setParent(BootstrapComponent parent) {
		this.parent = parent;
	}
	
	public String getPageId() {
		return this.parent.getPageId();
	}
	
	public Page getPage() {
		Optional<Page> optional = pageCache.get(getPageId());
		return optional.orElseThrow(()->new IllegalStateException("No Page with Id <" +getPageId()+"> registered All BootStrapComponents need a Page as Root-Component!"));
	}
	
	@SuppressWarnings("unchecked")
	public T addStyle(Style style) {
		this.styles.add(style);
		if (isRendered())
			this.webSocket.convertAndSend("/topic/base/css", new Css(getCssClass(), getId()));
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T removeStyle(Style style) {
		this.styles.remove(style);
		if (isRendered())
			this.webSocket.convertAndSend("/topic/base/css", new Css(getCssClass(), getId()));
		return (T) this;
	}

	@SuppressWarnings("unchecked")
	public T setStyle(Style style) {
		this.styles.clear();
		this.styles.addAll(getBaseStytes());
		this.styles.add(style);
		if (isRendered())
			this.webSocket.convertAndSend("/topic/base/css", new Css(getCssClass(), getId()));
		return (T) this;
	}
	
	public abstract Set<Style> getBaseStytes();
	
	public String getCssClass() {
		return styles.stream().map(Style::getCssClass).collect(Collectors.joining(" "));
	}
	
	public Set<Style> getStyles() {
		return this.styles;
	}
}