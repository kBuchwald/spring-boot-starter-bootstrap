package de.kb.bootstrap.components.alerts;

import java.util.Arrays;
import java.util.HashSet;
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
public class Alert extends AbstractBootstrapComponentWithSubComponents<Alert> implements BootstrapComponent {
	private boolean dismissable = false;
	
	@PostConstruct
	protected void init() {
		super.init();
		this.setStyle(Style.ALERT_PRIMARY);
	}

	@Override
	public Set<Style> getBaseStytes() {
		return new HashSet<>(Arrays.asList(Style.ALERT_BASE));
	}

	public boolean isDismissable() {
		return this.dismissable;
	}

	public Alert setDismissable(boolean dismissable) {
		this.dismissable = dismissable;
		if(dismissable)
			addStyle(Style.ALERT_DISMISSABLE);
		else
			removeStyle(Style.ALERT_DISMISSABLE);
		return this;
	}
}