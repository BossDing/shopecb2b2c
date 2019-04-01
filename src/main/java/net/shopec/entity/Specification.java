package net.shopec.entity;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.baomidou.mybatisplus.annotations.TableField;

/**
 * Entity - 规格
 * 
 */
public class Specification extends OrderedEntity<Specification> {

	private static final long serialVersionUID = -6346775052811140926L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 绑定分类
	 */
	@TableField(exist = false)
	private ProductCategory productCategory;

	/**
	 * 可选项
	 */
	@TableField(exist = false)
	private List<String> options = new ArrayList<>();

	/**
	 * 获取名称
	 * 
	 * @return 名称
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置名称
	 * 
	 * @param name
	 *            名称
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取绑定分类
	 * 
	 * @return 绑定分类
	 */
	public ProductCategory getProductCategory() {
		return productCategory;
	}

	/**
	 * 设置绑定分类
	 * 
	 * @param productCategory
	 *            绑定分类
	 */
	public void setProductCategory(ProductCategory productCategory) {
		this.productCategory = productCategory;
	}

	/**
	 * 获取可选项
	 * 
	 * @return 可选项
	 */
	public List<String> getOptions() {
		return options;
	}

	/**
	 * 设置可选项
	 * 
	 * @param options
	 *            可选项
	 */
	public void setOptions(List<String> options) {
		this.options = options;
	}

	@Override
	public int compareTo(Specification o) {
		if (o == null) {
			return 1;
		}
		return new CompareToBuilder().append(getOrder(), o.getOrder()).append(getId(), o.getId()).toComparison();
	}

}