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
    private ApiKey idApp;
    private String name;
    private String description;
    private Boolean enable;
    private Account fkUserCreator;

    public Application(ApiKey idApp, String name, String description, Boolean enable, Account fkUserCreator )
    {
        this.idApp = idApp;
        this.name = name;
        this.description = description;
        this.enable = enable;
        this.fkUserCreator = fkUserCreator;
    }
    /**
     * @return the idApp
     */
    public ApiKey getApiKey() {
        return idApp;
    }

    /**
     * @param idApp the idApp to set
     */
    public void setApiKey(ApiKey idApp) {
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
     * @return the fkUserCreator
     */
    public Account getIdUserCreator() {
        return fkUserCreator;
    }

    /**
     * @param fkUserCreator the idCreator to set
     */
    public void setIdCreator(Account fkUserCreator) {
        this.fkUserCreator = fkUserCreator;
    }

    
    
    
}
