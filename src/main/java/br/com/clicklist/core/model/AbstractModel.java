package br.com.clicklist.core.model;

import java.io.Serializable;

import javax.persistence.Transient;

import br.com.clicklist.core.util.DateTimeType;

/**
 * @author LSDuarte
 *
 */
public abstract class AbstractModel implements Serializable, DateTimeType {

	private static final long serialVersionUID = 1L;

	@Transient
	private boolean selected;

	public Boolean getSelected() {
		return selected;
	}

	public Boolean isSelected() {
		return selected;
	}

	public void setSelected(Boolean selected) {
		this.selected = selected;
	}

	@Override
	public boolean equals(Object obj) {
		boolean result = obj != null;
		result = result && getClass().isInstance(obj);
		result = result && hashCode() > 0;
		result = result && obj.hashCode() > 0;
		result = result && hashCode() == obj.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return String.valueOf(hashCode());
	}
}