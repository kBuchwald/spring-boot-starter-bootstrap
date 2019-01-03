package de.kb.bootstrap.components.content;

import java.util.Collections;
import java.util.Set;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.kb.bootstrap.components.AbstractBootstrapComponentWithSubComponents;
import de.kb.bootstrap.components.Style;
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SubContent extends AbstractBootstrapComponentWithSubComponents<SubContent> implements Content {

	@Override
	public Set<Style> getBaseStytes() {
		return Collections.emptySet();
	}
}