package tbdb4java.bussiness.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tbdb4java.bussiness.SAGenres;
import tbdb4java.integration.DAOCachedInfo;
import tbdb4java.integration.DAOGenres;
import tbdb4java.integration.factories.DAOType;
import tbdb4java.integration.factories.DAOCachedInfoFactory;
import tbdb4java.integration.factories.DAOGenresFactory;
import tbdb4java.model.CachedInfo;
import tbdb4java.model.Genre;
import tbdb4java.util.DatesUtil;

public class SAGenresImp implements SAGenres {
	
	private static final String GENRELIST_CACHE_TAG = "Genre_List";

	@Override
	public List<Genre> getGenresList(String language) {
		if(language == null)
			language = "es";
		List<Genre> genresList = new ArrayList<Genre>();
		
		
		DAOGenresFactory factoria = DAOGenresFactory.getInstance();
		DAOGenres daoCache = factoria.getDaoGenres(DAOType.CACHE);
		
		DAOCachedInfo daoCI = DAOCachedInfoFactory.getInstance().getDaoCachedInfo(DAOType.CACHE);
		CachedInfo ci = daoCI.getCachedInfo(GENRELIST_CACHE_TAG);

		if ((ci == null || DatesUtil.DistanceFromNowIsMoreThan(ci.getDate(), 30))){ //se actualizan los generos cada 3s0días
			
			DAOGenres daoOnline = factoria.getDaoGenres(DAOType.ONLINE);
			
			genresList = daoOnline.getGenres(language);
			
			if(ci != null){ //si ya existían, y se quieren actualizar
				List<Genre> cachedList = daoCache.getGenres(language);
				for(Genre g : cachedList)
					daoCache.removeGenre(g);
				daoCI.removeCachedInfo(ci);
			}
			
			for(Genre g : genresList)
				daoCache.addGenre(g);
			
			ci = new CachedInfo(GENRELIST_CACHE_TAG, new Date(), 1);
			daoCI.addCachedInfo(ci);
			
		}else{
			genresList = daoCache.getGenres(language);
		}
		
		
		return genresList;
	}

}
