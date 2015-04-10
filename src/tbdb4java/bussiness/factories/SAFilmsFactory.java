package tbdb4java.bussiness.factories;

import tbdb4java.bussiness.SAFilms;
import tbdb4java.bussiness.factories.imp.SAFilmsFactoryImp;

public abstract class SAFilmsFactory {

	private static SAFilmsFactory instance;
	public static SAFilmsFactory getInstance(){
		if(instance == null)
			instance = new SAFilmsFactoryImp();
		return instance;
	}
	
	public abstract SAFilms getSAFilms();
}
