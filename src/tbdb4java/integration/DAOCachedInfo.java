package tbdb4java.integration;

import java.util.List;

import tbdb4java.model.CachedInfo;

public interface DAOCachedInfo {

	List<CachedInfo> getCachedInfoList();
	CachedInfo getCachedInfo(String consulta);
	boolean addCachedInfo(CachedInfo cachedInfo);
	boolean updateCachedInfo(CachedInfo cachedInfo);
	boolean removeCachedInfo(CachedInfo cachedInfo);
	
}
