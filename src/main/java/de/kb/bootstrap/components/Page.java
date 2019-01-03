package de.kb.bootstrap.components;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Page extends AbstractBootstrapComponentWithSubComponents<Page> implements BootstrapComponent {
	private transient Map<String, BootstrapComponent> componentsById = new HashMap<>();
	private transient Map<String, List<BootstrapComponent>> componentsByType = new HashMap<>();
	@Autowired
	private transient ApplicationContext applicationContext;

	@PostConstruct
	protected void init() {
		super.init();
		applicationContext.getBean(PageCache.class).put(this);
	}

	public Map<String, BootstrapComponent> getComponentsById() {
		return this.componentsById;
	}

	public Map<String, List<BootstrapComponent>> getComponentsByType() {
		return this.componentsByType;
	}

	public void subComponetAdded(BootstrapComponent component) {
		allSubComponents(component).forEach(subComponent -> {
			componentsById.put(subComponent.getId(), subComponent);
			String key = subComponent.getClass().getSimpleName().toLowerCase();
			List<BootstrapComponent> list = componentsByType.get(key);
			if (list == null)
				list = new ArrayList<>();
			list.add(subComponent);
			componentsByType.put(key, list);
		});
	}

	public void setRendered() {
		super.setRendered();
		componentsById.values().forEach(BootstrapComponent::setRendered);
	}
	
	@Override
	public String getPageId() {
		return getId();
	}
	
	@Override
	public Set<Style> getBaseStytes() {
		return Collections.emptySet();
	}
}