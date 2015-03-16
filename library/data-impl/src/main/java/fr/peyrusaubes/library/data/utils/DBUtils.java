package fr.peyrusaubes.library.data.utils;

import java.lang.reflect.Field;
import java.text.MessageFormat;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.peyrusaubes.library.common.exception.ExceptionCodes;
import fr.peyrusaubes.library.common.exception.LibraryTechnicalException;

/**
 * Database utilitary class.
 * 
 * @author jcpeyrusaubes
 * 
 */
public final class DBUtils {
	private static Logger log = LoggerFactory.getLogger(DBUtils.class);

	private DBUtils() {

	}

	/**
	 * Get the datasource of the entity manager.
	 * 
	 * @param entityManager
	 *            Entity manager
	 * @return Datasource configured for the entity manager
	 * @throws IBeaconTechnicalException
	 *             exception
	 */
	public static DataSource getDatasource(EntityManager entityManager)
			throws LibraryTechnicalException {
		try {
			EntityManagerFactory entityManagerFactory = entityManager
					.getEntityManagerFactory();
			Field jtaDatasourceField = entityManagerFactory.getClass()
					.getDeclaredField("jtaDataSource");
			jtaDatasourceField.setAccessible(true);
			try {
				return (DataSource) jtaDatasourceField
						.get(entityManagerFactory);
			} finally {
				jtaDatasourceField.setAccessible(false);
			}

		} catch (IllegalArgumentException e) {
			handleException(log, e);
		} catch (IllegalAccessException e) {
			handleException(log, e);
		} catch (SecurityException e) {
			handleException(log, e);
		} catch (NoSuchFieldException e) {
			handleException(log, e);
		}

		return null;
	}

	/**
	 * Handle exception logging and encapsulate original exception.
	 * 
	 * @param pLog
	 *            Logger of the calling class
	 * @param e
	 *            exception to handle
	 * @throws IBeaconTechnicalException
	 *             exception
	 */
	public static void handleException(Logger pLog, Exception e)
			throws LibraryTechnicalException {
		pLog.error(e.getMessage(), e);
		throw new LibraryTechnicalException(ExceptionCodes.TECH_DATA_ERROR,
				MessageFormat.format(ExceptionCodes.TECH_DATA_ERROR_MSG,
						e.getMessage()), e);
	}
}
