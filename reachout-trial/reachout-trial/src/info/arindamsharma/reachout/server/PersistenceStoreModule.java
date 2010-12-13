package info.arindamsharma.reachout.server;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;

import com.google.inject.Binder;
import com.google.inject.Inject;
import com.google.inject.Module;
import com.google.inject.Provider;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.servlet.RequestScoped;

public class PersistenceStoreModule implements Module {

  @Override
  public void configure(Binder binder) {
    
  }
  
  @Inject @Provides @RequestScoped
  public PersistenceManager getPersistenceManager(
          Provider<PersistenceManagerFactory> pmfProvider) {
    return pmfProvider.get().getPersistenceManager();
  }
  
  @Provides @Singleton
  public PersistenceManagerFactory getPeristenceManagerFactory() {
    return JDOHelper.getPersistenceManagerFactory("transactions-optional");
  }
}
