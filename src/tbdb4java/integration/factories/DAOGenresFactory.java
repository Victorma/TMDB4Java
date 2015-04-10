package tbdb4java.integration.factories;

import tbdb4java.integration.DAOGenres;
import tbdb4java.integration.factories.imp.DAOGenresFactoryImp;

public abstract class DAOGenresFactory {

	private static DAOGenresFactory instance;
	public static DAOGenresFactory getInstance(){
		if(instance == null)
			instance = new DAOGenresFactoryImp();
		return instance;
	}
	
	public abstract DAOGenres getDaoGenres(DAOType daoType);
	
}
