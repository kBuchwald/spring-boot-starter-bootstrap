package de.kb.bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

@Configuration
public class ThymeleafConfig {
	private static final String CHARACTER_ENCODING = "UTF-8";
	
	@Autowired
	private ApplicationContext applicationContext;

	@Bean
	public ViewResolver htmlViewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setContentType("text/html");
		resolver.setCharacterEncoding(CHARACTER_ENCODING);
		resolver.setViewNames(new String[] { "*.html" });
		return resolver;
	}

	@Bean
	public ViewResolver javascriptViewResolver() {
		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(templateEngine());
		resolver.setContentType("application/javascript");
		resolver.setCharacterEncoding(CHARACTER_ENCODING);
		resolver.setViewNames(new String[] { "*.js" });
		return resolver;
	}

	@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setMessageSource(applicationContext);
		engine.addTemplateResolver(htmlTemplateResolver());
		engine.addTemplateResolver(javascriptTemplateResolver());
		return engine;
	}

	private ITemplateResolver htmlTemplateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setOrder(0);
		resolver.setCheckExistence(true);
		resolver.setApplicationContext(applicationContext);
		resolver.setPrefix("classpath:templates/");
		resolver.setCacheable(false);
		resolver.setTemplateMode(TemplateMode.HTML);
		resolver.setSuffix(".html");
		return resolver;
	}

	public ITemplateResolver javascriptTemplateResolver() {
		SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
		resolver.setApplicationContext(applicationContext);
		resolver.setOrder(1);
		resolver.setCheckExistence(true);
		resolver.setPrefix("classpath:/js/");
		resolver.setCacheable(false);
		resolver.setTemplateMode(TemplateMode.JAVASCRIPT);
		resolver.setSuffix(".js");
		return resolver;
	}
}