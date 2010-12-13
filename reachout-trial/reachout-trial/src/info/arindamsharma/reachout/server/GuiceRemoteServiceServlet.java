package info.arindamsharma.reachout.server;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.gwt.user.client.rpc.IncompatibleRemoteServiceException;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

@SuppressWarnings("serial")
@Singleton
public class GuiceRemoteServiceServlet extends RemoteServiceServlet {
  private static final Logger logger = Logger.getLogger(GuiceRemoteServiceServlet.class.getSimpleName());
  @Inject
  private Injector injector;
  
  @Override
  public String processCall(String payload) throws SerializationException {
    try {
      RPCRequest req = RPC.decodeRequest(payload, null, this);      
      RemoteService service = getServiceInstance(req.getMethod().getDeclaringClass());
      return RPC.invokeAndEncodeResponse(service, req.getMethod(), req.getParameters(), req.getSerializationPolicy());
    } catch (IncompatibleRemoteServiceException e) {
      logger.log(Level.SEVERE, "IncompatibleRemoteServiceException in processCall() ", e);
      return RPC.encodeResponseForFailure(null, e);
    }
  }

  private RemoteService getServiceInstance(Class<?> declaringClass) {
    return (RemoteService) injector.getInstance(declaringClass);
  }
}
