package de.kb.bootstrap.components;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/base")
public class BaseBootstrapController {
	@Autowired
	private transient ApplicationContext applicationContext;

	@GetMapping(path = "js/{pageId}", produces = "text/javascript")
	public ModelAndView js(@PathVariable String pageId) {
		Optional<Page> optional = applicationContext.getBean(PageCache.class).get(pageId);
		if (optional.isPresent()) {
			ModelAndView mav = new ModelAndView("base.js");
			mav.addObject(BootstrapModelAndView.PAGE_ATTRIBUTE, optional.get());
			return mav;
		}
		return null;
	}

	@GetMapping(path = "contentByParent/{pageId}/{parentId}", produces = "text/html")
	public ModelAndView contentByParent(@PathVariable String pageId, @PathVariable String parentId) {
		Optional<Page> optional = applicationContext.getBean(PageCache.class).get(pageId);
		if (optional.isPresent()) {
			BootstrapComponent parent = optional.get().getComponentsById().get(parentId);
			if (parent == null)
				return null;
			ModelAndView mav = new ModelAndView("content::component");
			mav.addObject(BootstrapModelAndView.PAGE_ATTRIBUTE, optional.get());
			mav.addObject("contents", parent.getSubComponents());
			return mav;
		}
		return null;
	}

	@GetMapping(path = "content/{pageId}/{contentId}", produces = "text/html")
	public ModelAndView content(@PathVariable String pageId, @PathVariable String contentId) {
		Optional<Page> optional = applicationContext.getBean(PageCache.class).get(pageId);
		if (optional.isPresent()) {
			BootstrapComponent content = optional.get().getComponentsById().get(contentId);
			if (content == null)
				return null;
			ModelAndView mav = new ModelAndView("content::component");
			mav.addObject(BootstrapModelAndView.PAGE_ATTRIBUTE, optional.get());
			mav.addObject("contents", Arrays.asList(content));
			return mav;
		}
		return null;
	}

	@GetMapping(path = "contentByParent/js/{pageId}/{parentId}", produces = "text/javascript")
	public ModelAndView contentByParentJs(@PathVariable String pageId, @PathVariable String parentId) {
		Optional<Page> optional = applicationContext.getBean(PageCache.class).get(pageId);
		if (optional.isPresent()) {
			ModelAndView mav = new ModelAndView("content.js");
			mav.addObject(BootstrapModelAndView.PAGE_ATTRIBUTE, optional.get());
			BootstrapComponent parent = optional.get().getComponentsById().get(parentId);
			if (parent == null)
				return null;
			mav.addObject("components", parent.getSubComponents());
			return mav;
		}
		return null;
	}

	@GetMapping(path = "contentByPage/js/{pageId}", produces = "text/javascript")
	public ModelAndView contentByPage(@PathVariable String pageId) {
		Optional<Page> optional = applicationContext.getBean(PageCache.class).get(pageId);
		if (optional.isPresent()) {
			ModelAndView mav = new ModelAndView("content.js");
			mav.addObject(BootstrapModelAndView.PAGE_ATTRIBUTE, optional.get());
			List<BootstrapComponent> allComponents = optional.get().getComponentsByType().values().stream().flatMap(list -> list.stream()).collect(Collectors.toList());
			mav.addObject("components", allComponents);
			return mav;
		}
		return null;
	}

	@GetMapping(path = "content/js/{pageId}/{contentId}", produces = "text/javascript")
	public ModelAndView contentJs(@PathVariable String pageId, @PathVariable String contentId) {
		Optional<Page> optional = applicationContext.getBean(PageCache.class).get(pageId);
		if (optional.isPresent()) {
			BootstrapComponent content = optional.get().getComponentsById().get(contentId);
			if (content == null)
				return null;
			ModelAndView mav = new ModelAndView("content.js");
			mav.addObject(BootstrapModelAndView.PAGE_ATTRIBUTE, optional.get());
			mav.addObject("components", Arrays.asList(content));
			return mav;
		}
		return null;
	}
}