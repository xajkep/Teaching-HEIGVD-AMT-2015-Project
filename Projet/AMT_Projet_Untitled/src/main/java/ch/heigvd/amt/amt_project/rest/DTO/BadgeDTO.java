package ch.heigvd.amt.amt_project.rest.DTO;

/**
 *
 * @author thsch
 */


public class BadgeDTO {
    private String id;
    private String picture;
    private String description;
    
    public String getId(){
        return id;
    }
    
    public String getPicture(){
        return picture;
    }
    
    public String getDescription(){
        return description;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setPicture(String picture){
        this.picture = picture;
    }
    
    public void setDescription(String description){
        this.description = description;
    }
    
}
