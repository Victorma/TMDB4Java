package tbdb4java.bussiness.factories;

import tbdb4java.bussiness.SAGenres;
import tbdb4java.bussiness.factories.imp.SAGenresFactoryImp;

public abstract class SAGenresFactory {

	private static SAGenresFactory instance;
	public static SAGenresFactory getInstance(){
		if(instance == null)
			instance = new SAGenresFactoryImp();
		return instance;
	}
	
	public abstract SAGenres getSAGenres();
	
	
}
