package de.kb.bootstrap.components;

import org.springframework.web.servlet.ModelAndView;

public class BootstrapModelAndView extends ModelAndView {
	private static final String PAGE_VIEW = "page";
	public static final String PAGE_ATTRIBUTE = "page";
	private final Page page;

	public BootstrapModelAndView(Page page) {
		super(PAGE_VIEW);
		this.page = page;
		addObject(PAGE_ATTRIBUTE, page);
	}
	
	public Page getPage() {
		return page;
	}
}