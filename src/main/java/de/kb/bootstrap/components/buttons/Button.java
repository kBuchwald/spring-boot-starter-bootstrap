package de.kb.bootstrap.components.buttons;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Scope;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import de.kb.bootstrap.components.AbstractBootstrapComponent;
import de.kb.bootstrap.components.BootstrapComponent;
import de.kb.bootstrap.components.Style;
import de.kb.bootstrap.components.content.MessageKeyContent;
import de.kb.bootstrap.components.content.TextContent;
import de.kb.bootstrap.components.content.TextualContent;
import de.kb.bootstrap.components.content.TextualContentVisitor;
import de.kb.bootstrap.components.events.ClickListener;
import de.kb.bootstrap.components.events.Registration;
import de.kb.bootstrap.components.websocket_messages.Disable;
import de.kb.bootstrap.components.websocket_messages.Text;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Button extends AbstractBootstrapComponent<Button> implements BootstrapComponent {
	private TextualContent content;
	private List<ClickListener> listeners = new ArrayList<>();
	private boolean disabled = false;
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private ApplicationContext applicationContext;

	@PostConstruct
	protected void init() {
		super.init();
		this.setStyle(Style.BUTTON_PRIMARY);
	}

	public String getText() {
		if (content instanceof TextContent)
			return ((TextContent) content).getText();
		return null;
	}

	public Button setText(String text) {
		if(this.content == null)
			this.content = applicationContext.getBean(TextContent.class).setText(text);
		else
			this.content.accept(new TextualContentVisitor() {
				@Override
				public void visit(MessageKeyContent visitee) {
					Button.this.content = applicationContext.getBean(TextContent.class).setText(text);
				}
				
				@Override
				public void visit(TextContent visitee) {
					visitee.setText(text);
				}
			});
		if (isRendered())
			this.webSocket.convertAndSend("/topic/content/text", new Text(text, getId()));
		return this;
	}

	public String getMsgKey() {
		if (content instanceof MessageKeyContent)
			return ((MessageKeyContent) content).getMsgKey();
		return null;
	}

	public Button setMsgKey(String msgKey) {
		if(this.content == null)
			this.content = applicationContext.getBean(MessageKeyContent.class).setMsgKey(msgKey);
		else
			this.content.accept(new TextualContentVisitor() {
				@Override
				public void visit(MessageKeyContent visitee) {
					visitee.setMsgKey(msgKey);
				}
				
				@Override
				public void visit(TextContent visitee) {
					Button.this.content = applicationContext.getBean(MessageKeyContent.class).setMsgKey(msgKey);
				}
			});
		
			if(isRendered())
				this.webSocket.convertAndSend("/topic/content/text", new Text(this.messageSource.getMessage(msgKey, null, LocaleContextHolder.getLocale()),getId()));
		return this;
	}

	public void clicked() {
		listeners.forEach(ClickListener::onClick);
	}

	public Registration addClickListener(ClickListener listener) {
		this.listeners.add(listener);
		return () -> listeners.remove(listener);
	}

	public Button disable() {
		this.disabled = true;
		if (isRendered())
			this.webSocket.convertAndSend("/topic/button/disable", new Disable(disabled, getId()));
		return this;
	}

	public Button switchDisabledState() {
		if (disabled)
			this.disabled = false;
		else
			this.disabled = true;
		if (isRendered())
			this.webSocket.convertAndSend("/topic/button/disable", new Disable(disabled, getId()));
		return this;
	}

	@Override
	public Set<Style> getBaseStytes() {
		return new HashSet<>(Arrays.asList(Style.BUTTON_BASE));
	}
}