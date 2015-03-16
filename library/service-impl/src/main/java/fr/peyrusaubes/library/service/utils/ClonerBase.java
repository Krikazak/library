package fr.peyrusaubes.library.service.utils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.peyrusaubes.library.model.common.PersistentEntity;


/**
 * Base implementation of the <code>Cloner</code> interface.
 * 
 * @author jcpeyrusaubes
 * 
 * @param <E>
 */
public class ClonerBase<E extends PersistentEntity<K>, K> implements Cloner<E, K> {

	/**
	 * Cycle handling mode type.
	 * 
	 * @author jcpeyrusaubes
	 * 
	 */
	public enum CycleHandling {
		HANDLE_CYCLE_WITH_NULL, HANDLE_CYCLE_WITH_OBJECT, HANDLE_CYCLE_WITH_ID
	}

	private static Logger log = LoggerFactory.getLogger(ClonerBase.class);

	@SuppressWarnings("rawtypes")
	private Map<String, ClonerBase> includedEntities = new HashMap<String, ClonerBase>();

	private CycleHandling cycleHandling = CycleHandling.HANDLE_CYCLE_WITH_ID;

	private static ThreadLocal<Map<PersistentEntity<?>, PersistentEntity<?>>> encounteredEntities = new ThreadLocal<Map<PersistentEntity<?>, PersistentEntity<?>>>();

	/**
	 * Updates cycle handling mode type.
	 * 
	 * @param cycleHandling
	 *            Cycle handling mode type.
	 * @return Updated cloner
	 */
	public ClonerBase<E, K> handleCycle(CycleHandling cycleHandling) {
		this.cycleHandling = cycleHandling;

		for (@SuppressWarnings("rawtypes") ClonerBase cloner : includedEntities.values()) {
			cloner.handleCycle(this.cycleHandling);
		}

		return this;
	}

	/**
	 * Defines which entity attributes must be cloned (by default, only simple attributes are cloned).<br/>
	 * 
	 * @param entities
	 *            String describing entities to clone.<br/>
	 *            Attributs are separated by commas, and the . notation
	 *            allows to run through the entity hierarchy.
	 * @return Updated cloner
	 */
	@SuppressWarnings({ "rawtypes" })
	public ClonerBase<E, K> include(String entities) {
		if (entities == null || entities.trim().length() == 0) {
			return this;
		}

		for (String propertyPath : entities.split(",")) {
			propertyPath = propertyPath.trim();
			String[] propertyNames = propertyPath.split("\\.", 2);
			String propertyName = propertyNames[0];

			ClonerBase propertyCloner = includedEntities.get(propertyName);
			if (propertyCloner == null) {
				propertyCloner = new ClonerBase().handleCycle(this.cycleHandling);
				includedEntities.put(propertyName, propertyCloner);
			}
			if (propertyNames.length > 1) {
				propertyCloner.include(propertyNames[1]);
			}
		}

		return this;
	}

	/**
	 * Create a copy of the original object. Copy only simple and unmutable
	 * properties like {@link String} ou {@link Boolean}. Calls copySpecific to
	 * handle entity properties copy.
	 * 
	 * @param original
	 *            Original entity
	 * @return a copy according to the contract
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public E copy(E original) {
		E copy = null;
		if (original == null) {
			return null;
		}

		boolean init = false;
		if (encounteredEntities.get() == null) {
			init = true;
			encounteredEntities.set(new HashMap<PersistentEntity<?>, PersistentEntity<?>>());
		}

		if (encounteredEntities.get().containsKey(original)) {
			copy = (E) encounteredEntities.get().get(original);
		} else {
			try {
				copy = (E) original.getClass().newInstance();
				switch (this.cycleHandling) {
				case HANDLE_CYCLE_WITH_ID:
					encounteredEntities.get().put(original, new ClonerId().copy(original));
					break;
				case HANDLE_CYCLE_WITH_OBJECT:
					encounteredEntities.get().put(original, copy);
					break;
				default:
					encounteredEntities.get().put(original, null);
					break;
				}

				PropertyDescriptor[] descriptors = PropertyUtils.getPropertyDescriptors(original);
				for (PropertyDescriptor descriptor : descriptors) {
					Cloner entityCloner = includedEntities.get(descriptor.getName());

					if (entityCloner != null) {
						try {
							if (Collection.class.isAssignableFrom(descriptor.getPropertyType())) {
								PropertyUtils.setSimpleProperty(copy, descriptor.getName(),
										entityCloner.copy((Collection) PropertyUtils.getSimpleProperty(original, descriptor.getName())));
							} else {
								PersistentEntity originalEntity = (PersistentEntity) PropertyUtils.getSimpleProperty(original, descriptor.getName());

								PropertyUtils.setSimpleProperty(copy, descriptor.getName(), entityCloner.copy(originalEntity));
							}
						} catch (IllegalAccessException e) {
							log.error(e.getMessage(), e);
						} catch (InvocationTargetException e) {
							log.error(e.getMessage(), e);
						} catch (NoSuchMethodException e) {
							log.error(e.getMessage(), e);
						}
					} else if (descriptor.getPropertyType() != null && !PersistentEntity.class.isAssignableFrom(descriptor.getPropertyType())
							&& !Collection.class.isAssignableFrom(descriptor.getPropertyType()) && descriptor.getWriteMethod() != null) {
						try {
							PropertyUtils.setSimpleProperty(copy, descriptor.getName(), PropertyUtils.getSimpleProperty(original, descriptor.getName()));
						} catch (IllegalAccessException e) {
							log.error(e.getMessage(), e);
						} catch (InvocationTargetException e) {
							log.error(e.getMessage(), e);
						} catch (NoSuchMethodException e) {
							log.error(e.getMessage(), e);
						}
					}
				}

				E result = copySpecific(original, copy);

				if (init) {
					encounteredEntities.remove();
				}

				return result;
			} catch (InstantiationException e) {
				log.error(e.getMessage(), e);
			} catch (IllegalAccessException e) {
				log.error(e.getMessage(), e);
			}
		}

		return copy;
	}

	/**
	 * Copy a collection of originals using the copy(E original) method on each
	 * element.
	 * 
	 * @param originals
	 *            Original collection
	 * @return List of copy
	 */
	public List<E> copy(Collection<? extends E> originals) {
		if (originals == null) {
			return null;
		}
		List<E> copys = new ArrayList<E>();
		for (E original : originals) {
			copys.add(copy(original));
		}
		return copys;
	}

	/**
	 * Handles specific copy of entity properties from original to copy
	 * parameter.
	 * 
	 * @param original
	 *            Original entity
	 * @param copy
	 *            Copy to be completed
	 * @return completed copy
	 */
	public E copySpecific(E original, E copy) {
		return copy;
	}

}
