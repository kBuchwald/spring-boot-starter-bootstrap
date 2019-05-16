package de.kb.bootstrap.components;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import de.kb.bootstrap.components.alerts.Alert;
import de.kb.bootstrap.components.buttons.Button;
import de.kb.bootstrap.components.content.MessageKeyContent;
import de.kb.bootstrap.components.content.TextContent;
import de.kb.bootstrap.components.content.TextualContent.Type;
import de.kb.bootstrap.components.headlines.Headline;
import de.kb.bootstrap.components.hr.Hr;
import de.kb.bootstrap.components.navigation.Navbar;
import de.kb.bootstrap.components.navigation.NavbarMenuItem;

@Controller
@RequestMapping(value = "/")
public class MainController {
	@Autowired
	private transient ApplicationContext applicationContext;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView get() {
		Button button1 = applicationContext.getBean(Button.class).setMsgKey("test");
		Button button2 = applicationContext.getBean(Button.class).setText("TestText");
		Button button3 = applicationContext.getBean(Button.class).setMsgKey("test.remove");
		Button button4 = applicationContext.getBean(Button.class).setMsgKey("test.add");
		Button button5 = applicationContext.getBean(Button.class).setMsgKey("test");
		Button button6 = applicationContext.getBean(Button.class).setMsgKey("test");
		button1.addClickListener(() -> button2.switchDisabledState());
		button2.addClickListener(() -> button1.switchDisabledState());
		button1.addClickListener(() -> button3.setStyle(Style.BUTTON_OUTLINE_DARK));
		button1.addClickListener(() -> button3.setText("ganz anderer Text"));
		button3.addClickListener(() -> button6.remove());
		Navbar navbar = new Navbar().addNavItems(new NavbarMenuItem());
		Page page = applicationContext.getBean(Page.class).add(navbar).add(button1).add(button2).add(button3).add(button4).add(button5).add(button6);
		button4.addClickListener(() -> {
			Button button = applicationContext.getBean(Button.class).setMsgKey("test.added");
			button.addClickListener(() -> button.remove());
			page.add(button);
		});
		Alert alert = applicationContext.getBean(Alert.class).setDismissable(true);
		page.add(alert);
		Headline headline = applicationContext.getBean(Headline.class).setStyle(Style.H3);
		alert.add(headline);
		headline.add(applicationContext.getBean(MessageKeyContent.class).setMsgKey("alert.headline").setType(Type.PLAIN));
		alert.add(applicationContext.getBean(MessageKeyContent.class).setType(Type.PARAGRAPH).setMsgKey("alert.text")).add(applicationContext.getBean(Hr.class))
				.add(applicationContext.getBean(MessageKeyContent.class).setType(Type.PARAGRAPH).setMsgKey("alert.more"));
		return new BootstrapModelAndView(page);
	}

	@RequestMapping(value = "universalData", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ModelAndView postUniversalData(@RequestBody Object bodyData) {
		System.out.println(bodyData);
		ModelAndView mav = new ModelAndView("start");
		return mav;
	}
}