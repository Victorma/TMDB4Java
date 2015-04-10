package tbdb4java.bussiness.factories.imp;

import tbdb4java.bussiness.SAFilms;
import tbdb4java.bussiness.factories.SAFilmsFactory;
import tbdb4java.bussiness.imp.SAFilmsImp;

public class SAFilmsFactoryImp extends SAFilmsFactory {

	@Override
	public SAFilms getSAFilms() {
		return new SAFilmsImp();
	}

}
