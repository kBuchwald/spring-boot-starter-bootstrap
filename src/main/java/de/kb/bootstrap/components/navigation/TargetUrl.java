package de.kb.bootstrap.components.navigation;

import java.net.URL;

public class TargetUrl implements NavigateTo{
	private final URL url;
	
	public TargetUrl(URL url) {
		super();
		this.url = url;
	}

	public URL getUrl() {
		return this.url;
	}

	@Override
	public <T extends NavigateToVisitor> T accept(T visitor) {
		visitor.visit(this);
		return visitor;
	}
}