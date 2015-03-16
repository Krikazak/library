package fr.peyrusaubes.library.model.common;

import java.io.Serializable;

public abstract class PersistentEntity<K> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 3594184306651599105L;

	private Boolean onlyId;
	
	public abstract K getId();
	
	public abstract void setId(K id);
	
	public PersistentEntity() {
		super();
	}
	
	public PersistentEntity(K id) {
		this();
		this.setId(id);
	}
	
	@Override
	public int hashCode() {
		if (getId() == null) {
			return 0;
		} else {
			return getId().hashCode();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj.getClass() != this.getClass()) {
			return false;
		}
		
		@SuppressWarnings("unchecked")
		PersistentEntity<K> entity = (PersistentEntity<K>) obj;
		
		if (entity.getId() == null) {
			if (this.getId() == null) {
				return this == entity;
			} else {
				return false;
			}
		}
		
		return (entity.getId().equals(this.getId()));
	}

	public Boolean getOnlyId() {
		return onlyId;
	}

	public void setOnlyId(Boolean onlyId) {
		this.onlyId = onlyId;
	}

}
