package info.arindamsharma.reachout.server;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.servlet.GuiceServletContextListener;

public class GuiceContextListener extends GuiceServletContextListener {

  @Override
  protected Injector getInjector() {
    return Guice.createInjector(
            new GWTServletsModule(),
            new PersistenceStoreModule());
  }

}
