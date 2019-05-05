package de.kb.bootstrap.components.navigation;

import java.net.URI;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.accept.MediaTypeFileExtensionResolver;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import de.kb.bootstrap.components.AbstractBootstrapComponentWithSubComponents;
import de.kb.bootstrap.components.Style;
import de.kb.bootstrap.components.buttons.Button;
import de.kb.bootstrap.components.content.MessageKeyContent;
import de.kb.bootstrap.components.content.TextContent;
import de.kb.bootstrap.components.content.TextualContent;
import de.kb.bootstrap.components.content.TextualContentVisitor;
import de.kb.bootstrap.components.websocket_messages.AnchorLink;
import de.kb.bootstrap.components.websocket_messages.Css;
import de.kb.bootstrap.components.websocket_messages.Image;
import de.kb.bootstrap.components.websocket_messages.ImageHeight;
import de.kb.bootstrap.components.websocket_messages.ImageWidth;
import de.kb.bootstrap.components.websocket_messages.Text;
import de.kb.bootstrap.resourcces.ImageRessource;

public class Brand extends AbstractBootstrapComponentWithSubComponents<Brand> {
	private NavigateTo navigateTo;
	private TextualContent linkText;
	private ImageRessource image;
	private int imageWidth;
	private int imageHeight;
	private Set<Style> imageStyles;
	
	@Autowired
	private MessageSource messageSource;
	@Autowired
	private MediaTypeFileExtensionResolver extensionResolver;

	@PostConstruct
	public void inir() {
		super.init();
	}

	@Override
	public Set<Style> getBaseStytes() {
		return new HashSet<>(Arrays.asList(Style.NAVBAR_BRAND));
	}

	public NavigateTo getNavigateTo() {
		return this.navigateTo;
	}

	public Brand setNavigateTo(NavigateTo navigateTo) {
		this.navigateTo = navigateTo;
		if (isRendered()) {
			this.navigateTo.accept(new NavigateToVisitor() {
				@Override
				public void visit(OpenModal visitee) {
					Brand.this.webSocket.convertAndSend("/topic/modal/openModal", visitee);
				}

				@Override
				public void visit(TargetUrl visitee) {
					Brand.this.webSocket.convertAndSend("/topic/anchor/link", new AnchorLink(getId(), visitee.getUrl().toString()));
				}
			});
		}
		return this;
	}

	public TextualContent getLinkText() {
		return this.linkText;
	}

	public Brand setLinkText(TextualContent linkText) {
		this.linkText = linkText;
		if (isRendered()) {
			this.linkText.accept(new TextualContentVisitor() {
				@Override
				public void visit(MessageKeyContent visitee) {
					Brand.this.webSocket.convertAndSend("/topic/content/text", new Text(Brand.this.messageSource.getMessage(visitee.getMsgKey(), null, LocaleContextHolder.getLocale()), getId()));
				}

				@Override
				public void visit(TextContent visitee) {
					Brand.this.webSocket.convertAndSend("/topic/content/text", new Text(visitee.getText(), getId()));
				}
			});
		}
		return this;
	}

	public ImageRessource getImage() {
		return this.image;
	}

	public Brand setImage(ImageRessource image) {
		this.image = image;
		if(isRendered()) {
			StringBuilder filename = new StringBuilder(image.getFileName());
			String extension = extensionResolver.resolveFileExtensions(image.getMediaType()).get(0);
			if(StringUtils.isNotEmpty(extension))
				filename.append('.').append(extension);
			this.webSocket.convertAndSend("/topic/content/image", new Image(image.getId(), filename.toString()));
		}
		return this;
	}

	public int getImageWidth() {
		return this.imageWidth;
	}

	public Brand setImageWidth(int imageWidth) {
		this.imageWidth = imageWidth;
		if(isRendered())if(isRendered())
			this.webSocket.convertAndSend("/topic/content/image/width", new ImageWidth(image.getId(), imageWidth));
		return this;
	}

	public int getImageHeight() {
		return this.imageHeight;
	}

	public Brand setImageHeight(int imageHeight) {
		this.imageHeight = imageHeight;
		if(isRendered())if(isRendered())
			this.webSocket.convertAndSend("/topic/content/image/height", new ImageHeight(image.getId(), imageHeight));
		return this;
	}

	public Brand addStyle(Style style) {
		this.imageStyles.add(style);
		if (isRendered())
			this.webSocket.convertAndSend("/topic/base/css", new Css(getImageCssClass(), getImage().getId()));
		return this;
	}

	public Brand removeStyle(Style style) {
		this.imageStyles.remove(style);
		if (isRendered())
			this.webSocket.convertAndSend("/topic/base/css", new Css(getImageCssClass(), getImage().getId()));
		return this;
	}

	public Brand setStyle(Style style) {
		this.imageStyles.clear();
		this.imageStyles.add(style);
		if (isRendered())
			this.webSocket.convertAndSend("/topic/base/css", new Css(getImageCssClass(), getImage().getId()));
		return this;
	}

	
	public String getImageCssClass() {
		return imageStyles.stream().map(Style::getCssClass).collect(Collectors.joining(" "));
	}
	
	public Set<Style> getImageStyles() {
		return this.imageStyles;
	}
}