package tbdb4java.integration.factories.imp;

import tbdb4java.integration.DAOFilms;
import tbdb4java.integration.cache.imp.DAOFilmsCache;
import tbdb4java.integration.factories.DAOType;
import tbdb4java.integration.factories.DAOFilmsFactory;
import tbdb4java.integration.online.imp.DAOFilmsOnline;

public class DAOFilmsFactoryImp extends DAOFilmsFactory{


	@Override
	public DAOFilms getDaoFilms(DAOType daoType) {
		DAOFilms dao = null;
		
		switch(daoType){
		case CACHE: dao = new DAOFilmsCache(); break;
		case ONLINE: 
			try{
			dao = new DAOFilmsOnline(); 
			}catch(RuntimeException re){
				re.printStackTrace();
			}
			
			break;
		}
		
		return dao;
	}
}
