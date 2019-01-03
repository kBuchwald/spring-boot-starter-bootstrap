package de.kb.bootstrap.components.navigation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.kb.bootstrap.components.AbstractBootstrapComponent;
import de.kb.bootstrap.components.Style;
import de.kb.bootstrap.components.content.TextualContent;
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NavbarNavigateToItem extends AbstractBootstrapComponent<NavbarNavigateToItem> implements NavItem{
	
	private NavigateTo navigateTo;
	private TextualContent text;
	
	@PostConstruct
	public void init() {
		super.init();
	}

	@Override
	public Set<Style> getBaseStytes() {
		return new HashSet<>(Arrays.asList(Style.NAVBAR_NAVITEM));
	}

	public NavigateTo getNavigateTo() {
		return this.navigateTo;
	}

	public void setNavigateTo(NavigateTo navigateTo) {
		this.navigateTo = navigateTo;
	}

	public TextualContent getText() {
		return this.text;
	}

	public void setText(TextualContent text) {
		this.text = text;
	}
}