/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amt_project.services.dao;

import ch.heigvd.amt.amt_project.models.ApiKey;
import javax.ejb.Stateless;

/**
 *
 * @author YounTheory
 */
@Stateless
public class ApiKeysDAO extends GenericDAO<ApiKey, Long> implements ApiKeysDAOLocal {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
}
