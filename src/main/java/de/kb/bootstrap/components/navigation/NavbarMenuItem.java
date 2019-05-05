package de.kb.bootstrap.components.navigation;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import de.kb.bootstrap.components.AbstractBootstrapComponent;
import de.kb.bootstrap.components.Style;

public class NavbarMenuItem extends AbstractBootstrapComponent<NavbarMenuItem> implements NavItem{

	@Override
	public Set<Style> getBaseStytes() {
		return new HashSet<>(Arrays.asList(Style.NAVBAR_NAVITEM, Style.DROPDOWN));
	}}
