package com.pedroalmir.athena.common.util.vraptor;


import br.com.caelum.vraptor.ioc.Container;

import com.pedroalmir.athena.common.annotation.MainDAO;
import com.pedroalmir.athena.common.annotation.MainDAOInterface;
import com.pedroalmir.athena.common.repository.GenericDAO;


/**
 * Class with utility methods for the container object VRaptor
 * 
 * @author Pedro Oliveira
 */
public class ContainerUtil {

	/**
	 * 
	 * Use the container to return an instance of a DAO, 
	 * which is found in DAO annotation MainDAO
	 * 
	 * @param container
	 *            Object Container
	 * @param daoInterface
	 *            Some extent MainDAO interface, the method to be used setMainDAO
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static void setMainDAO(Container container, MainDAOInterface daoInterface) {
		if (daoInterface.getClass().isAnnotationPresent(MainDAO.class)) {
			MainDAO mainDAO = daoInterface.getClass().getAnnotation(MainDAO.class);
			GenericDAO dao = container.instanceFor(mainDAO.value());
			dao.setContainer(container);
			daoInterface.setMainDAO(dao);
		}
	}

}
