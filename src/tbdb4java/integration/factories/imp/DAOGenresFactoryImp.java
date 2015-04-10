package tbdb4java.integration.factories.imp;

import tbdb4java.integration.DAOGenres;
import tbdb4java.integration.cache.imp.DAOGenresCache;
import tbdb4java.integration.factories.DAOType;
import tbdb4java.integration.factories.DAOGenresFactory;
import tbdb4java.integration.online.imp.DAOGenresOnline;

public class DAOGenresFactoryImp extends DAOGenresFactory{

	@Override
	public DAOGenres getDaoGenres(DAOType daoType) {
		DAOGenres dao = null;
		
		switch(daoType){
		case CACHE: dao = new DAOGenresCache(); break;
		case ONLINE: dao = new DAOGenresOnline(); break;
		}
		
		return dao;
	}


}
