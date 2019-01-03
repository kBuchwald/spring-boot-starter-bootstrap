package de.kb.bootstrap.components;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

@Component
@SessionScope
public class PageCache {
	private Map<String, Page> pages = new HashMap<>();
	
	public Optional<Page> get(String id){
		return Optional.ofNullable(pages.get(id));
	}
	
	public void put(Page page) {
		pages.put(page.getId(), page);
	}
	
	public void remove(String id) {
		pages.remove(id);
	}
	
	public void remove(Page page) {
		pages.remove(page.getId());
	}
}