package tbdb4java.integration.cache.imp;

import java.util.List;

import tbdb4java.integration.DAOCachedInfo;
import tbdb4java.integration.cache.DAOCache;
import tbdb4java.model.CachedInfo;

public class DAOCachedInfoCache implements DAOCache, DAOCachedInfo {

	@Override
	public List<CachedInfo> getCachedInfoList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CachedInfo getCachedInfo(String consulta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addCachedInfo(CachedInfo cachedInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateCachedInfo(CachedInfo cachedInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeCachedInfo(CachedInfo cachedInfo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getCreateQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDeleteQuery() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
