package tbdb4java.integration.factories.imp;

import tbdb4java.integration.DAOCachedInfo;
import tbdb4java.integration.cache.imp.DAOCachedInfoCache;
import tbdb4java.integration.factories.DAOType;
import tbdb4java.integration.factories.DAOCachedInfoFactory;

public class DAOCachedInfoFactoryImp extends DAOCachedInfoFactory {


	@Override
	public DAOCachedInfo getDaoCachedInfo(DAOType daoType) {
		return new DAOCachedInfoCache();
	}
	
}
