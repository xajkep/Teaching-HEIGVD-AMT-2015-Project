package ch.heigvd.amt.amt_project.services;

import ch.heigvd.amt.amt_project.models.Application;
import javax.ejb.Stateless;

/**
 *  Data Access Object for Application
 * 
 * @author mberthouzoz
 */
@Stateless
public class ApplicationsDAO extends GenericDAO<Application, Long> implements ApplicationsDAOLocal {
  
  /**
   * Disable the application.
   * 
   * @param app Application to disable
   */
  public void disable(Application app) {
      app.setEnable(Boolean.FALSE);      
  }
  
  
  /**
   * Enable the application.
   * 
   * @param app Application to enable
   */
  public void enable(Application app) {
      app.setEnable(Boolean.TRUE);      
  }
 
}
