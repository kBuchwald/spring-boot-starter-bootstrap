package de.kb.bootstrap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import de.kb.bootstrap.components.BootstrapModelAndView;
import de.kb.bootstrap.components.Page;

public class ComponentRenderedInterceptor implements HandlerInterceptor {
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		if (modelAndView != null) {
			Object object = modelAndView.getModel().get(BootstrapModelAndView.PAGE_ATTRIBUTE);
			if (object != null && object instanceof Page)
				((Page) object).setRendered();
		}
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}
}