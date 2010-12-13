package info.arindamsharma.reachout.server;

import info.arindamsharma.reachout.client.service.GetUserService;
import info.arindamsharma.reachout.client.service.SaveUserService;
import info.arindamsharma.reachout.shared.RpcConstants;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.inject.servlet.ServletModule;

public class GWTServletsModule extends ServletModule {
  private static final String BIND_URL_LOG_MSG = "Binding url /%s to Service '%s'";
  private static final Logger logger = Logger.getLogger(GWTServletsModule.class.getSimpleName());
  
  @Override
  protected void configureServlets() {
    bindDomain(RpcConstants.GET_USER, GetUserService.class, UserActions.class);
    bindDomain(RpcConstants.SAVE_USER, SaveUserService.class, UserActions.class);
  }
  
  private <T> void bindDomain(String url, Class<T> serviceClazz, Class<? extends T> implClazz) {
    serve("/" + url).with(GuiceRemoteServiceServlet.class);
    bind(serviceClazz).to(implClazz);
    logger.log(Level.INFO, String.format(BIND_URL_LOG_MSG, url, implClazz.getName()));
  }
}
