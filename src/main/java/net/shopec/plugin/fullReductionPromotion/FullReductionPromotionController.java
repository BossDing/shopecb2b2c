package net.shopec.plugin.fullReductionPromotion;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import net.shopec.Message;
import net.shopec.controller.admin.BaseController;
import net.shopec.entity.PluginConfig;
import net.shopec.entity.Promotion;
import net.shopec.plugin.PromotionPlugin;
import net.shopec.service.PluginConfigService;
import net.shopec.service.PromotionService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * Controller - 满减促销
 * 
 */
@Controller("adminFullReductionPromotionController")
@RequestMapping("/admin/promotion_plugin/full_reduction_promotion")
public class FullReductionPromotionController extends BaseController {

	@Inject
	private FullReductionPromotionPlugin fullReductionPromotionPlugin;
	@Inject
	private PluginConfigService pluginConfigService;
	@Inject
	private PromotionService promotionService;

	/**
	 * 安装
	 */
	@PostMapping("/install")
	public @ResponseBody Message install() {
		if (!fullReductionPromotionPlugin.getIsInstalled()) {
			PluginConfig pluginConfig = new PluginConfig();
			pluginConfig.setPluginId(fullReductionPromotionPlugin.getId());
			pluginConfig.setIsEnabled(false);
			pluginConfig.setAttributes(null);
			pluginConfigService.save(pluginConfig);
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 卸载
	 */
	@PostMapping("/uninstall")
	public @ResponseBody Message uninstall() {
		if (fullReductionPromotionPlugin.getIsInstalled()) {
			pluginConfigService.deleteByPluginId(fullReductionPromotionPlugin.getId());
			promotionService.shutDownPromotion(Promotion.Type.fullReduction);
		}
		return SUCCESS_MESSAGE;
	}

	/**
	 * 设置
	 */
	@GetMapping("/setting")
	public String setting(ModelMap model) {
		PluginConfig pluginConfig = fullReductionPromotionPlugin.getPluginConfig();
		model.addAttribute("pluginConfig", pluginConfig);
		return "/net/shopec/plugin/fullReductionPromotion/setting";
	}

	/**
	 * 更新
	 */
	@PostMapping("/update")
	public String update(BigDecimal price, @RequestParam(defaultValue = "false") Boolean isEnabled, Integer order, RedirectAttributes redirectAttributes) {
		if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
			return ERROR_VIEW;
		}
		PluginConfig pluginConfig = fullReductionPromotionPlugin.getPluginConfig();
		Map<String, String> attributes = new HashMap<>();
		pluginConfig.setAttributes(attributes);
		attributes.put(PromotionPlugin.PRICE, price.toString());
		pluginConfig.setIsEnabled(isEnabled);
		pluginConfig.setOrder(order);
		pluginConfigService.update(pluginConfig);
		if (!pluginConfig.getIsEnabled()) {
			promotionService.shutDownPromotion(Promotion.Type.fullReduction);
		}
		addFlashMessage(redirectAttributes, SUCCESS_MESSAGE);
		return "redirect:/admin/promotion_plugin/list";
	}

}