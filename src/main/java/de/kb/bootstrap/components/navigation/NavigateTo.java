package de.kb.bootstrap.components.navigation;

public interface NavigateTo {
	<T extends NavigateToVisitor> T accept(T visitor);
}