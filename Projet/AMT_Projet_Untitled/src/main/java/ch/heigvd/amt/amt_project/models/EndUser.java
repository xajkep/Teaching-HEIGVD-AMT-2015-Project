/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amt_project.models;

import java.util.Date;

/**
 *
 * @author YounTheory
 */
public class EndUser {
    private Application idApp;
    private String name;
    private Date date;

    public EndUser(Application idApp, String name, Date date)
    {
        this.idApp = idApp;
        this.name = name;
        this.date = date;
    }
    
    /**
     * @return the idApp
     */
    public Application getFkApp() {
        return idApp;
    }

    /**
     * @param idApp the idApp to set
     */
    public void setFkApp(Application idApp) {
        this.idApp = idApp;
    }

    /**
     * @return the name
     */
    public String getFkUser() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setFkUser(String name) {
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
