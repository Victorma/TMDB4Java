package tbdb4java.bussiness.factories.imp;

import tbdb4java.bussiness.SAGenres;
import tbdb4java.bussiness.factories.SAGenresFactory;
import tbdb4java.bussiness.imp.SAGenresImp;

public class SAGenresFactoryImp extends SAGenresFactory {

	@Override
	public SAGenres getSAGenres() {
		return new SAGenresImp();
	}

}
