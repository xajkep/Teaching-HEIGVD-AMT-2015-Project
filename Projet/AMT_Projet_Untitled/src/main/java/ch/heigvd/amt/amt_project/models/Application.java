/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amt_project.models;

/**
 *
 * @author YounTheory
 */
public class Application {
    private String idApp;
    private String name;
    private String description;
    private Boolean enable;
    private int idUserCreator;

    public Application(String idApp, String name, String description, Boolean enable, int idUserCreator )
    {
        this.idApp = idApp;
        this.name = name;
        this.description = description;
        this.enable = enable;
        this.idUserCreator = idUserCreator;

    
    }
    /**
     * @return the idApp
     */
    public String getApiKey() {
        return idApp;
    }

    /**
     * @param idApp the idApp to set
     */
    public void setApiKey(String idApp) {
        this.idApp = idApp;
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
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the enable
     */
    public Boolean getEnable() {
        return enable;
    }

    /**
     * @param enable the enable to set
     */
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    /**
     * @return the idUserCreator
     */
    public int getIdUserCreator() {
        return idUserCreator;
    }

    /**
     * @param idUserCreator the idCreator to set
     */
    public void setIdCreator(int idUserCreator) {
        this.idUserCreator = idUserCreator;
    }

    
    
    
}
