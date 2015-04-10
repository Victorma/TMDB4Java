package tbdb4java.bussiness.imp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import tbdb4java.bussiness.SAFilms;
import tbdb4java.bussiness.orders.DiscoverFilmsOrder;
import tbdb4java.bussiness.orders.TopFilmsOrder;
import tbdb4java.integration.DAOCachedInfo;
import tbdb4java.integration.DAOFilms;
import tbdb4java.integration.factories.DAOType;
import tbdb4java.integration.factories.DAOCachedInfoFactory;
import tbdb4java.integration.factories.DAOFilmsFactory;
import tbdb4java.model.CachedInfo;
import tbdb4java.model.Film;
import tbdb4java.model.Genre;
import tbdb4java.util.DatesUtil;

public class SAFilmsImp implements SAFilms {

	private static final String FILM = "film_";
	private static final String DISCOVER = "discover_";
	private static final String SEARCH = "search_";
	private static final String TOP = "top_";

	private boolean isOnline() {
		//TODO repair check if it's online
		return true;
	}

	/*
	 * Film
	 */

	@Override
	public Film getFilm(Film film, String language) {
		return getFilm(film.getId(), language);
	}

	@Override
	public Film getFilm(int filmId, String language) {

		DAOFilmsFactory fFilms = DAOFilmsFactory.getInstance();
		DAOFilms daoCache = fFilms.getDaoFilms(DAOType.CACHE);

		DAOCachedInfo daoCachedInfo = DAOCachedInfoFactory
				.getInstance().getDaoCachedInfo(DAOType.CACHE);

		CachedInfo ci = daoCachedInfo.getCachedInfo(FILM + filmId);
		Film film = daoCache.getFilm(filmId, language);

		if (isOnline()
				&& (ci == null
						|| DatesUtil.DistanceFromNowIsMoreThan(ci.getDate(), 7) || !film
							.isCompletada())) {
			DAOFilms daoOnline = fFilms.getDaoFilms(DAOType.ONLINE);

			film = daoOnline.getFilm(filmId, language);

			if (ci != null) {
				daoCachedInfo.removeCachedInfo(ci);
				daoCache.updateFilm(film);
			} else
				daoCache.addFilm(film);

			ci = new CachedInfo(FILM + filmId, new Date(), 1);
			daoCachedInfo.addCachedInfo(ci);
		}

		return film;
	}

	/*
	 * Films By Genre
	 */

	@Override
	public List<Film> getFilmsByGenre(Genre genre, String order, String language, int page) {
		return getFilmsByGenre(genre.getId(), order, language, page);
	}

	@Override
	public List<Film> getFilmsByGenre(int genreId, String order, String language, int page) {
		return discoverFilms(genreId, null, null, null, null, order, language, page);
	}

	/*
	 * Discover Films
	 */

	@Override
	public List<Film> discoverFilms(Genre genre, Date ini, Date fin,
			Float calificacionMinima, Integer votosMinimo, String order,
			String language, int page) {

		return discoverFilms(
				(Integer) ((genre != null) ? genre.getId() : null), ini, fin,
				calificacionMinima, votosMinimo, order, language, page);
	}

	@Override
	public List<Film> discoverFilms(Integer genreId, Date ini, Date fin,
			Float calificacionMinima, Integer votosMinimo, String order,
			String language, int page) {

		List<Film> r = new ArrayList<Film>();

		DAOFilmsFactory fFilms = DAOFilmsFactory.getInstance();
		DAOFilms daoCache = fFilms.getDaoFilms(DAOType.CACHE);

		DAOCachedInfo daoCachedInfo = DAOCachedInfoFactory
				.getInstance().getDaoCachedInfo(DAOType.CACHE);

		String ordenDiscover = order;

		String discoverCachedInfoQuery = DISCOVER
				+ ((genreId != null) ? genreId : null) + "_"
				+ ((ini != null) ? ini.getTime() : null) + "_"
				+ ((fin != null) ? fin.getTime() : null) + "_"
				+ ((calificacionMinima != null) ? calificacionMinima : null)
				+ "_" + ((votosMinimo != null) ? votosMinimo : null) + "_"
				+ language + "_" + ordenDiscover;

		CachedInfo ci = daoCachedInfo.getCachedInfo(discoverCachedInfoQuery);

		if (isOnline()
				&& (ci == null
						|| DatesUtil.DistanceFromNowIsMoreThan(ci.getDate(), 2) || page > ci
						.getLastPage())) {
			DAOFilms daoOnline = fFilms.getDaoFilms(DAOType.ONLINE);

			r = daoOnline.discoverFilms(genreId, ini, fin, calificacionMinima,
					votosMinimo, language, ordenDiscover, page);

			if (genreId != null)
				for (Film f : r) {
					List<Genre> genres = new ArrayList<Genre>();
					genres.add(new Genre(genreId, null));
					f.setGenres(genres);
				}

			revisaFilmsEnCache(r, language);

			if (ci != null)
				daoCachedInfo.removeCachedInfo(ci);

			daoCachedInfo.addCachedInfo(new CachedInfo(discoverCachedInfoQuery,
					new Date(), page));
		} else {
			r = daoCache.discoverFilms(genreId, ini, fin, calificacionMinima,
					votosMinimo, language, ordenDiscover, page);
		}

		return r;
	}

	/*
	 * Rest Methods
	 */

	@Override
	public List<Film> searchFilms(String query, String order, String language,
			int page) {
		List<Film> r = null;

		DAOFilmsFactory fFilms = DAOFilmsFactory.getInstance();
		DAOFilms daoCache = fFilms.getDaoFilms(DAOType.CACHE);

		DAOCachedInfo daoCachedInfo = DAOCachedInfoFactory
				.getInstance().getDaoCachedInfo(DAOType.CACHE);
		query = query.replace(" ", "+");
		String searchCachedInfoQuery = SEARCH + query;

		String ordenDiscover = order;

		CachedInfo ci = daoCachedInfo.getCachedInfo(searchCachedInfoQuery);

		if (isOnline()) { // en este caso damos prioridad a la busqueda online
							// ya que su sistema de búsqueda es mucho más
							// avanzado
			DAOFilms daoOnline = fFilms.getDaoFilms(DAOType.ONLINE);

			r = daoOnline.searchFilms(query, ordenDiscover, language, page);
			revisaFilmsEnCache(r, language);

			if (ci != null)
				daoCachedInfo.removeCachedInfo(ci);

			daoCachedInfo.addCachedInfo(new CachedInfo(searchCachedInfoQuery,
					new Date(), page));
		} else {
			r = daoCache.searchFilms(query, ordenDiscover, language, page);
		}

		return r;
	}

	@Override
	public List<Film> topFilms(String order, String language, int page) {
		List<Film> r = null;

		DAOFilmsFactory fFilms = DAOFilmsFactory.getInstance();
		DAOFilms daoCache = fFilms.getDaoFilms(DAOType.CACHE);

		DAOCachedInfo daoCachedInfo = DAOCachedInfoFactory
				.getInstance().getDaoCachedInfo(DAOType.CACHE);

		String ordenTop = order;

		if (ordenTop != null && ordenTop.equals(TopFilmsOrder.Newest))
			r = lastFilms(language, page);
		else {

			String topCachedInfoQuery = TOP + ordenTop;

			CachedInfo ci = daoCachedInfo.getCachedInfo(topCachedInfoQuery);

			if (isOnline()
					&& (ci == null
							|| DatesUtil.DistanceFromNowIsMoreThan(
									ci.getDate(), 1) || page > ci.getLastPage())) {
				DAOFilms daoOnline = fFilms.getDaoFilms(DAOType.ONLINE);

				r = daoOnline.getTopFilms(language, ordenTop, page);
				revisaFilmsEnCache(r, language);

				if (ci != null)
					daoCachedInfo.removeCachedInfo(ci);

				daoCachedInfo.addCachedInfo(new CachedInfo(topCachedInfoQuery,
						new Date(), page));
			} else {
				r = daoCache.getTopFilms(language, ordenTop, page);
			}
		}

		return r;
	}

	private void revisaFilmsEnCache(List<Film> r, String language) {

		DAOCachedInfo daoCachedInfo = DAOCachedInfoFactory
				.getInstance().getDaoCachedInfo(DAOType.CACHE);
		DAOFilms daoCache = DAOFilmsFactory.getInstance().getDaoFilms(
				DAOType.CACHE);
		DAOFilms daoOnline = DAOFilmsFactory.getInstance().getDaoFilms(
				DAOType.CACHE);

		for (Film f : r) {
			CachedInfo filmCi = daoCachedInfo.getCachedInfo(FILM + f.getId());

			if (filmCi != null) {

				boolean genresChanged = false;
				if (f.getGenres() != null) {
					for (Genre cachedGenre : daoCache.getFilm(f.getId(), language)
							.getGenres())
						if (!f.getGenres().contains(cachedGenre)) {
							f.getGenres().add(cachedGenre);
							genresChanged = true;
						}
				}
				if (DatesUtil.DistanceFromNowIsMoreThan(filmCi.getDate(), 7)
						|| genresChanged) { // Si la peli que tengo guardada es
											// vieja la actualizo
					daoOnline.updateFilm(f);
					filmCi.setDate(new Date());
					daoCachedInfo.updateCachedInfo(filmCi);
				}

			} else {
				daoCachedInfo.addCachedInfo(new CachedInfo(FILM + f.getId(),
						new Date(), 1));
				daoCache.addFilm(f);
			}

		}
	}

	@Override
	public List<Film> lastFilms(String language, int page) {
		return discoverFilms((Genre) null, null, new Date(), null, null,
				language, DiscoverFilmsOrder.DateDesc, page);
	}

}
