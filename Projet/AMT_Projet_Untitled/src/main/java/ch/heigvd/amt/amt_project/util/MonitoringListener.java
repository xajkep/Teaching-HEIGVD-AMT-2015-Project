package ch.heigvd.amt.amt_project.util;

import ch.heigvd.amt.amt_project.services.TestDataManagerLocal;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 
 * @author mberthouzoz
 */
@WebListener
public class MonitoringListener implements ServletContextListener, HttpSessionListener, ServletRequestListener {

  TestDataManagerLocal testDataManager = lookupTestDataManagerLocal();

  private final AtomicLong numberOfSessionsCreated = new AtomicLong(0);
  private final AtomicLong numberOfSessionsDestroyed = new AtomicLong(0);
  private final AtomicLong numberOfRequestsCreated = new AtomicLong(0);
  private final AtomicLong numberOfRequestsDestroyed = new AtomicLong(0);

  public static final String STARTUP_TIME = "startupTime";
  public static final String MONITORING_LISTENER = "monitoringListener";

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    testDataManager.generateTestData();
    sce.getServletContext().setAttribute(STARTUP_TIME, System.currentTimeMillis());
    sce.getServletContext().setAttribute(MONITORING_LISTENER, this);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
  }

  @Override
  public void sessionCreated(HttpSessionEvent se) {
    numberOfSessionsCreated.incrementAndGet();
  }

  @Override
  public void sessionDestroyed(HttpSessionEvent se) {
    numberOfSessionsDestroyed.incrementAndGet();
  }

  @Override
  public void requestInitialized(ServletRequestEvent sre) {
    numberOfRequestsCreated.incrementAndGet();
  }

  @Override
  public void requestDestroyed(ServletRequestEvent sre) {
    numberOfRequestsDestroyed.incrementAndGet();
  }

  public long getNumberOfSessionsCreated() {
    return numberOfSessionsCreated.get();
  }

  public long getNumberOfSessionsDestroyed() {
    return numberOfSessionsDestroyed.get();
  }

  public long getNumberOfRequestsCreated() {
    return numberOfRequestsCreated.get();
  }


  public long getNumberOfRequestsDestroyed() {
    return numberOfRequestsDestroyed.get();
  }
  
  private TestDataManagerLocal lookupTestDataManagerLocal() {
    try {
      Context c = new InitialContext();
      String lookupValue = "java:global/AMT_Project/TestDataManager";

      return (TestDataManagerLocal) c.lookup(lookupValue);
    } catch (NamingException ne) {
      Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", ne);
      throw new RuntimeException(ne);
    }
  }

}
