package de.kb.bootstrap.components.navigation;

public interface NavigateToVisitor {
	void visit(TargetUrl visitee);
	void visit(OpenModal visitee);
}