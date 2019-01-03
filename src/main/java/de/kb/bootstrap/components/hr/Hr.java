package de.kb.bootstrap.components.hr;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.kb.bootstrap.components.AbstractBootstrapComponent;
import de.kb.bootstrap.components.Style;
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Hr extends AbstractBootstrapComponent<Hr>{

	@Override
	public Set<Style> getBaseStytes() {
		return Collections.emptySet();
	}
}