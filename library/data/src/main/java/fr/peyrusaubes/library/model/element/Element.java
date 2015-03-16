package fr.peyrusaubes.library.model.element;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fr.peyrusaubes.library.common.exception.LibraryTechnicalException;
import fr.peyrusaubes.library.common.utils.ParsingUtils;
import fr.peyrusaubes.library.model.common.PersistentEntity;
import fr.peyrusaubes.library.model.zone.Zone;

@Entity
@Table(name = "ELEMENT")
public class Element extends PersistentEntity<Long> {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = -5379422068151066220L;

	/**
	 * Unique identifier.
	 */
	@Id
	@GeneratedValue
	@Column(name = "ELT_ID")
	private Long id;

	/**
	 * Name.
	 */
	@Column(name = "ELT_NAME")
	@Size(max = 50)
	private String name;

	/**
	 * Zone containing this element.
	 */
	@ManyToOne
	@JoinColumn(name = "ZON_ID")
	private Zone container;

	/**
	 * Data.
	 */
	@Transient
	private Map data;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the container
	 */
	public Zone getContainer() {
		return container;
	}

	/**
	 * @param container
	 *            the container to set
	 */
	public void setContainer(Zone container) {
		this.container = container;
	}

	/**
	 * @return the data
	 * @throws LibraryTechnicalException 
	 */
	@Column(name = "ELT_DATA")
	@JsonIgnore
	public String getDataValue() throws LibraryTechnicalException {
		if (data == null) {
			return null;
		}
		return ParsingUtils.getInstance().objectToJsonString(data);
	}

	/**
	 * @param data
	 *            the data to set
	 * @throws LibraryTechnicalException 
	 */
	@JsonIgnore
	public void setDataValue(String data) throws LibraryTechnicalException {
		if (data == null || data.isEmpty()) {
			data = null;
		} else {
			this.data = ParsingUtils.getInstance().jsonStringToObject(data, Map.class);
		}
	}

	/**
	 * @return the data
	 */
	public Map getData() {
		return data;
	}

	/**
	 * @param data
	 *            the data to set
	 */
	public void setData(Map data) {
		this.data = data;
	}

}
