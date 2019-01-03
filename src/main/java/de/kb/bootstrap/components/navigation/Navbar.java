package de.kb.bootstrap.components.navigation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import de.kb.bootstrap.components.AbstractBootstrapComponent;
import de.kb.bootstrap.components.Style;
import de.kb.bootstrap.components.content.Content;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Navbar extends AbstractBootstrapComponent<Navbar> implements Content{
	
	private List<NavItem> navItems = new ArrayList<>();
	
	@PostConstruct
	public void init() {
		super.init();
		addStyle(Style.NAVBAR_EXPAND_LG).addStyle(Style.NAVBAR_LIGHT).addStyle(Style.BG_LIGHT);
	}

	@Override
	public Set<Style> getBaseStytes() {
		return new HashSet<>(Arrays.asList(Style.NAVBAR));
	}

	public List<NavItem> getNavItems() {
		return this.navItems;
	}

	public Navbar setNavItems(List<NavItem> navItems) {
		this.navItems = navItems;
		return this;
	}
	
	public Navbar addNavItems(NavItem navItems) {
		this.navItems.add(navItems);
		return this;
	}
	
	public Navbar addNavItems(int index, NavItem navItems) {
		this.navItems.add(index, navItems);
		return this;
	}
}