package de.kb.bootstrap.components;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import de.kb.bootstrap.components.websocket_messages.AddContent;
import de.kb.bootstrap.components.websocket_messages.Remove;
import de.kb.bootstrap.components.websocket_messages.SetContent;

public abstract class AbstractBootstrapComponentWithSubComponents<T extends BootstrapComponent> extends AbstractBootstrapComponent<T> {
	private List<BootstrapComponent> components = new ArrayList<>();

	@PostConstruct
	protected void init() {
		super.init();
	}

	public void setComponents(List<BootstrapComponent> components) {
		this.components = components;
		components.forEach(component -> component.setParent(this));
		components.forEach(component -> getPage().subComponetAdded(component));
		components.forEach(component -> component.addRemover(() -> components.remove(component)));
		if (isRendered())
			this.webSocket.convertAndSend("/topic/content/set", new SetContent(getId()));
	}

	private void addOneComponent(BootstrapComponent component) {
		component.setParent(this);
		getPage().subComponetAdded(component);
		component.addRemover(() -> components.remove(component));
	}

	@SuppressWarnings("unchecked")
	public T add(BootstrapComponent component) {
		this.components.add(component);
		addOneComponent(component);
		if (isRendered())
			this.webSocket.convertAndSend("/topic/content/add", new AddContent(getId(), component.getId()));
		return (T) this;
	}

	public void add(int index, BootstrapComponent component) {
		this.components.add(index, component);
		addOneComponent(component);
		if (isRendered()) {
			if (index > 0)
				this.webSocket.convertAndSend("/topic/content/addWithIndex", new AddContent(getId(), component.getId(), components.get(index - 1).getId()));
			else
				this.webSocket.convertAndSend("/topic/content/addWithIndex", new AddContent(getId(), component.getId(), true));
		}
	}

	public void remove(BootstrapComponent component) {
		component.remove();
		if (isRendered())
			this.webSocket.convertAndSend("/topic/content/remove", new Remove(component.getId()));
	}

	public void remove(String componentId) {
		BootstrapComponent component = getPage().getComponentsById().get(componentId);
		component.remove();
		if (isRendered())
			this.webSocket.convertAndSend("/topic/content/remove", new Remove(component.getId()));
	}

	public static List<BootstrapComponent> allSubComponents(BootstrapComponent component) {
		List<BootstrapComponent> components = new ArrayList<>();
		components.add(component);
		components.addAll(component.getSubComponents());
		component.getSubComponents().forEach(subComponent -> components.addAll(allSubComponents(subComponent)));
		return components;
	}

	public List<BootstrapComponent> getComponents() {
		return this.components;
	}
}