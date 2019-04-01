package net.shopec.listener;

import java.util.logging.Logger;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import net.shopec.entity.Article;
import net.shopec.entity.Product;
import net.shopec.entity.Store;
import net.shopec.service.ConfigService;
import net.shopec.service.SearchService;

/**
 * Listener - 初始化
 * 
 */
@Component
public class InitListener {

	/**
	 * Logger
	 */
	private static final Logger LOGGER = Logger.getLogger(InitListener.class.getName());

	@Value("${system.version}")
	private String systemVersion;

	@Inject
	private ConfigService configService;
	@Inject
	private SearchService searchService;

	/**
	 * 事件处理
	 * 
	 * @param contextRefreshedEvent
	 *            ContextRefreshedEvent
	 */
	@EventListener
	public void handle(ContextRefreshedEvent contextRefreshedEvent) {
		if (contextRefreshedEvent.getApplicationContext() == null || contextRefreshedEvent.getApplicationContext().getParent() != null) {
			return;
		}

		String info = "I|n|i|t|i|a|l|i|z|i|n|g| |S|H|O|P|E|C| |B|2|B|2|C| |" + systemVersion;
		LOGGER.info(info.replace("|", ""));
		configService.init();
		searchService.index(Article.class);
		searchService.index(Product.class);
		searchService.index(Store.class);
	}

}