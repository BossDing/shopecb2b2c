package net.shopec.entity;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.CompareToBuilder;

import com.baomidou.mybatisplus.annotations.TableField;

/**
 * Entity - 店铺等级
 * 
 */
public class StoreRank extends OrderedEntity<StoreRank> {

	private static final long serialVersionUID = -7588986748255635590L;

	/**
	 * 名称
	 */
	private String name;

	/**
	 * 服务费
	 */
	private BigDecimal serviceFee;

	/**
	 * 可发布商品数
	 */
	private Long quantity;

	/**
	 * 备注
	 */
	private String memo;

	/**
	 * 是否允许注册
	 */
	private Boolean isAllowRegister;

	/**
	 * 店铺
	 */
	@TableField(exist = false)
	private Set<Store> stores = new HashSet<>();

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
	 * 获取服务费
	 * 
	 * @return 服务费
	 */
	public BigDecimal getServiceFee() {
		return serviceFee;
	}

	/**
	 * 设置服务费
	 * 
	 * @param serviceFee
	 *            服务费
	 */
	public void setServiceFee(BigDecimal serviceFee) {
		this.serviceFee = serviceFee;
	}

	/**
	 * 获取可发布商品数
	 * 
	 * @return 可发布商品数
	 */
	public Long getQuantity() {
		return quantity;
	}

	/**
	 * 设置可发布商品数
	 * 
	 * @param quantity
	 *            可发布商品数
	 */
	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	/**
	 * 获取备注
	 * 
	 * @return 备注
	 */
	public String getMemo() {
		return memo;
	}

	/**
	 * 设置备注
	 * 
	 * @param memo
	 *            备注
	 */
	public void setMemo(String memo) {
		this.memo = memo;
	}

	/**
	 * 获取是否允许注册
	 * 
	 * @return 是否允许注册
	 */
	public Boolean getIsAllowRegister() {
		return isAllowRegister;
	}

	/**
	 * 设置是否允许注册
	 * 
	 * @param isAllowRegister
	 *            是否允许注册
	 */
	public void setIsAllowRegister(Boolean isAllowRegister) {
		this.isAllowRegister = isAllowRegister;
	}

	/**
	 * 获取店铺
	 * 
	 * @return 店铺
	 */
	public Set<Store> getStores() {
		return stores;
	}

	/**
	 * 设置店铺
	 * 
	 * @param stores
	 *            店铺
	 */
	public void setStores(Set<Store> stores) {
		this.stores = stores;
	}

	@Override
	public int compareTo(StoreRank o) {
		if (o == null) {
			return 1;
		}
		return new CompareToBuilder().append(getOrder(), o.getOrder()).append(getId(), o.getId()).toComparison();
	}

}