package de.kb.bootstrap.components;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

@Service
@SessionScope
public class IdGenerator {
	private static final Long FIRST = 1l;
	private final Map<String, Long> idMap = new HashMap<>();

	public String nextId(Identifyable identifyable) {
		String key = identifyable.getClass().getSimpleName().toLowerCase();
		return new StringBuilder(key).append(idMap.merge(key, FIRST, Long::sum)).toString();
	}

}