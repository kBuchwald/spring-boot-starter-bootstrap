package de.kb.bootstrap.components;

public enum Style {
	//Alert
	ALERT_BASE("alert"),
	ALERT_PRIMARY("alert-primary"), ALERT_SECONDARY("alert-secondary"), ALERT_SUCCESS("alert-success"), ALERT_DANGER("alert-danger"), ALERT_WARNING("alert-warning"), ALERT_INFO("alert-info"), ALERT_LIGHT("alert-light"),
	ALERT_DARK("alert-dark"), ALERT_LINK("btn-link"), ALERT_DISMISSABLE("alert-dismissible fade show"),
	//Button
	BUTTON_BASE("btn"), BUTTON_PRIMARY("btn-primary"), BUTTON_SECONDARY("btn-secondary"), BUTTON_SUCCESS("btn-success"), BUTTON_DANGER("btn-danger"), BUTTON_WARNING("btn-warning"), BUTTON_INFO("btn-info"), BUTTON_LIGHT("btn-light"),
	BUTTON_DARK("btn-dark"), BUTTON_LINK("btn-link"), BUTTON_OUTLINE_PRIMARY("btn-outline-primary"), BUTTON_OUTLINE_SECONDARY("btn-outline-secondary"), BUTTON_OUTLINE_SUCCESS("btn-outline-success"),
	BUTTON_OUTLINE_DANGER("btn-outline-danger"), BUTTON_OUTLINE_WARNING("btn-outline-warning"), BUTTON_OUTLINE_INFO("btn-outline-info"), BUTTON_OUTLINE_LIGHT("btn-outline-light"),
	BUTTON_OUTLINE_DARK("btn-outline-dark"), BUTTON_OUTLINE_LINK("btn-outline-link"), BUTTON_LARGE("btn-lg"), BUTTON_SMALL("btn-sm"), BUTTON_BLOCK("btn-block"),
	//Headlines
	H1("h1"),H2("h2"),H3("h3"),H4("h4"),H5("h5"),H6("h6"),DISPLAY_H1("display-1"),DISPLAY_H2("display-2"),DISPLAY_H3("display-3"),DISPLAY_H4("display-4"),
	//Navbar
	NAVBAR_BRAND("navbar-brand"), NAVBAR("navbar"), NAVBAR_LIGHT("navbar-light"),NAVBAR_EXPAND_LG("navbar-expand-lg"),NAVBAR_EXPAND_MR("navbar-expand-mr"),NAVBAR_NAVITEM("nav-item"),NAVBAR_NAVLIN("nav-link"),
	//DropDown
	DROPDOWN("dropdown"),DROPDOWN_MENU("dropdown-menu"),DROPDOWN_ITEM("dropdown-item"),DROPDOWN_DIVIDER("dropdown-divider"),DROPDOWN_TOGGLE("dropdown-toggle"),
	//Background
	BG_LIGHT("bg-light");
	private final String cssClass;

	private Style(String cssClass) {
		this.cssClass = cssClass;
	}

	public String getCssClass() {
		return cssClass;
	}
}