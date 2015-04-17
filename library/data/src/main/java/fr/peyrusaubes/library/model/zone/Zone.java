package fr.peyrusaubes.library.model.zone;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import fr.peyrusaubes.library.data.common.PersistenceContants;
import fr.peyrusaubes.library.model.common.PersistentEntity;

@Entity
@Table(name = "ZONE")
@NamedQueries({
	@NamedQuery(name=PersistenceContants.QUERY_NAME_ZONE_FIND_BY_PARENT, 
			query="SELECT z FROM Zone z WHERE (:parentId is null AND z.container.id is null) OR (z.container.id = :parentId)")
})
public class Zone extends PersistentEntity<Long> {

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 2097429272831957007L;
	
	/**
	 * Unique identifier.
	 */
	@Id
	@GeneratedValue
	@Column(name = "ZON_ID")
	private Long id;
	
	/**
	 * Name.
	 */
	@Column(name = "ZON_NAME")
	@Size(max = 50)
	private String name;
	
	/**
	 * Zone containing this one.
	 */
	@ManyToOne
	@JoinColumn(name = "ZON_CONTAINER_ID")
	private Zone container;
	
	/**
	 * Zone contained by this one.
	 */
	@OneToMany(mappedBy="container")
	private List<Zone> contained;
	
	/**
	 * Data.
	 */
	@Column(name = "ZON_DATA")
	private String data;
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the container
	 */
	public Zone getContainer() {
		return container;
	}

	/**
	 * @param container the container to set
	 */
	public void setContainer(Zone container) {
		this.container = container;
	}

	/**
	 * @return the contained
	 */
	public List<Zone> getContained() {
		return contained;
	}

	/**
	 * @param contained the contained to set
	 */
	public void setContained(List<Zone> contained) {
		this.contained = contained;
	}

	/**
	 * @return the data
	 */
	public String getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(String data) {
		this.data = data;
	}

}
