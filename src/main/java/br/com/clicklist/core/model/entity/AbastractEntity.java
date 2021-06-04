package br.com.clicklist.core.model.entity;

import br.com.clicklist.core.model.AbstractModel;
import br.com.clicklist.core.util.NumberUtil;

/**
 * Esta é uma classe abstrata, que deverá ser extendida por qualquer entidade.
 * 
 * 
 * @author LSDuarte
 */
public abstract class AbastractEntity extends AbstractModel {

	private static final long serialVersionUID = 1L;

	public abstract Long getId();

	public boolean isNewEntity() {
		return NumberUtil.isEmpty(getId());
	}

	@Override
	public int hashCode() {
		return getId() != null ? getId().hashCode() : super.hashCode();
	}

	public String getIdStr() {
		return toString();
	}
}
