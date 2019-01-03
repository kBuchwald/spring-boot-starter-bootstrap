package de.kb.bootstrap.components.buttons;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import de.kb.bootstrap.components.Page;
import de.kb.bootstrap.components.PageCache;

@Controller
@RequestMapping(value = "/button")
public class ButtonController {
	@Autowired
	private transient ApplicationContext applicationContext;

	@PostMapping(path="{buttonId}/{pageId}/click", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView click(@PathVariable String buttonId, @PathVariable String pageId) {
		Optional<Page> optional = applicationContext.getBean(PageCache.class).get(pageId);
		if(optional.isPresent()) {
			Button button = (Button)optional.get().getComponentsById().get(buttonId);
			button.clicked();
		}
		return null;
	}
}