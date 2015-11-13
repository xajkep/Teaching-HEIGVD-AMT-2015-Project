package ch.heigvd.amt.amt_project.rest.DTO;

import java.net.URI;


/**
 *
 * @author thsch
 */


public class BadgeDTO {
    private URI href;
    private String picture;
    private String description;
    
    public URI getHref(){
        return href;
    }
    
    public String getPicture(){
        return picture;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setHref(URI href){
        this.href = href;
    }
    
    public void setPicture(String picture){
        this.picture = picture;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
}
