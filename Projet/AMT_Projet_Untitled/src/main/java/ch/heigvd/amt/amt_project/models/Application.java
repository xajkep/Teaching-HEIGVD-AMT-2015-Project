/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.heigvd.amt.amt_project.models;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * This class implements the Application domain model object.
 *
 * @author mberthouzoz
 */
<<<<<<< HEAD
public class Application {
    private ApiKey idApp;
=======
@Entity
public class Application extends AbstractDomainModel<Long> {
    
>>>>>>> origin/master
    private String name;
    private String description;
    private String key;
    private Boolean enable;
<<<<<<< HEAD
    private Account fkUserCreator;

    public Application(ApiKey idApp, String name, String description, Boolean enable, Account fkUserCreator )
=======
    
    @ManyToOne
    private User creator;
    
    public Application() {
        
    }

    public Application(String name, String description, String key, Boolean enable, User creator )
>>>>>>> origin/master
    {
        this.key = key;
        this.name = name;
        this.description = description;
        this.enable = enable;
<<<<<<< HEAD
        this.fkUserCreator = fkUserCreator;
=======
        this.creator = creator;
>>>>>>> origin/master
    }
    /**
     * @return the key
     */
<<<<<<< HEAD
    public ApiKey getApiKey() {
        return idApp;
=======
    public String getKey() {
        return key;
>>>>>>> origin/master
    }

    /**
     * @param key the key to set
     */
<<<<<<< HEAD
    public void setApiKey(ApiKey idApp) {
        this.idApp = idApp;
=======
    public void setKey(String key) {
        this.key = key;
>>>>>>> origin/master
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
<<<<<<< HEAD
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
=======
     * @return the creator
     */
    public User getCreator() {
        return creator;
    }

    /**
     * @param creator the idCreator to set
     */
    public void setCreator(User creator) {
        this.creator = creator;
>>>>>>> origin/master
    }
    
}
