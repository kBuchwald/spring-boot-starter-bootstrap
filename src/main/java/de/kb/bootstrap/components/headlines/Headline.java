package de.kb.bootstrap.components.headlines;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.kb.bootstrap.components.AbstractBootstrapComponentWithSubComponents;
import de.kb.bootstrap.components.BootstrapComponent;
import de.kb.bootstrap.components.Style;
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Headline extends AbstractBootstrapComponentWithSubComponents<Headline> implements BootstrapComponent{
	
	@Override
	public Set<Style> getBaseStytes() {
		return Collections.emptySet();
	}
}