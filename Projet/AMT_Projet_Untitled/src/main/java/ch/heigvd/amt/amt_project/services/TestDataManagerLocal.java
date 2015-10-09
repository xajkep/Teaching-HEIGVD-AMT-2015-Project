package ch.heigvd.amt.amt_project.services;

import javax.ejb.Local;

/**
 *
 * @author mberthouzoz
 */
@Local
public interface TestDataManagerLocal {
    
    void generateTestData();
}
