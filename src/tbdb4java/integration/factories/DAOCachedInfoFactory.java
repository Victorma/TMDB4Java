package tbdb4java.integration.factories;

import tbdb4java.integration.DAOCachedInfo;
import tbdb4java.integration.factories.imp.DAOCachedInfoFactoryImp;

public abstract class DAOCachedInfoFactory {

	private static DAOCachedInfoFactory instance;
	public static DAOCachedInfoFactory getInstance(){
		if(instance == null)
			instance = new DAOCachedInfoFactoryImp();
		return instance;
	}
	
	public abstract DAOCachedInfo getDaoCachedInfo(DAOType daoType);
	
	
}
