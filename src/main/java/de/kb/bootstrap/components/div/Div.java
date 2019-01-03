package de.kb.bootstrap.components.div;

import java.util.Collections;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.kb.bootstrap.components.AbstractBootstrapComponentWithSubComponents;
import de.kb.bootstrap.components.BootstrapComponent;
import de.kb.bootstrap.components.Style;
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Div extends AbstractBootstrapComponentWithSubComponents<Div> implements BootstrapComponent {
	
	@PostConstruct
	protected void init() {
		super.init();
	}
	
	@Override
	public Set<Style> getBaseStytes() {
		return Collections.emptySet();
	}
}