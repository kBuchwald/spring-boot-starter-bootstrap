package de.kb.bootstrap.components;

import java.util.Collections;
import java.util.List;

public interface BootstrapComponent extends Identifyable{
	
	default List<BootstrapComponent> getSubComponents(){
		return Collections.emptyList();
	}
	
	default String getName() {
		return getClass().getSimpleName().toLowerCase();
	}
	
	boolean isRendered();
	
	void setRendered();
	
	void addRemover(ComponentRemover remover);
	
	void remove();
	
	String getPageId();
	
	void setParent(BootstrapComponent parent);
}