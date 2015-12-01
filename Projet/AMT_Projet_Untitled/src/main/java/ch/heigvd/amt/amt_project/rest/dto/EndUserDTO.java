package ch.heigvd.amt.amt_project.rest.dto;

import java.net.URI;

/**
 *
 * @author xajkep
 */
public class EndUserDTO {
    private URI href;
    private String name;

    public URI getHref() {
        return href;
    }

    public void setHref(URI href) {
        this.href = href;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
}
