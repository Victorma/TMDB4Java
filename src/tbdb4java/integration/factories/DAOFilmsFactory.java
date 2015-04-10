package tbdb4java.integration.factories;

import tbdb4java.integration.DAOFilms;
import tbdb4java.integration.factories.imp.DAOFilmsFactoryImp;


public abstract class DAOFilmsFactory {

	private static DAOFilmsFactory instance;
	public static DAOFilmsFactory getInstance(){
		if(instance == null)
			instance = new DAOFilmsFactoryImp();
		return instance;
	}
	
	public abstract DAOFilms getDaoFilms(DAOType daoType);
	
}
