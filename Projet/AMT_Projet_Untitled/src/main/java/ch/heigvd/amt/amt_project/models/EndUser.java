/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amt_project.models;

import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 *
 * @author , mberthouzoz
 */
@Entity
public class EndUser extends AbstractDomainModel<Long>{
    @ManyToOne
    private Application app;
    private String name;
    private Date date;
    
    public EndUser() {
        
    }

    public EndUser(Application app, String name, Date date)
    {
        this.app = app;
        this.name = name;
        this.date = date;
    }
    
    /**
     * @return the app
     */
    public Application getApp() {
        return app;
    }

    /**
     * @param app the app to set
     */
    public void setApp(Application app) {
        this.app = app;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
