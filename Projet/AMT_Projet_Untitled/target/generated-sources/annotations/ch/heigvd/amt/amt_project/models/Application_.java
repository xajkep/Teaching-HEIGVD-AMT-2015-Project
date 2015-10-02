package ch.heigvd.amt.amt_project.models;

import ch.heigvd.amt.amt_project.models.Account;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2015-10-02T09:29:15")
@StaticMetamodel(Application.class)
public class Application_ extends AbstractDomainModel_ {

    public static volatile SingularAttribute<Application, String> description;
    public static volatile SingularAttribute<Application, String> name;
    public static volatile SingularAttribute<Application, Boolean> enable;
    public static volatile SingularAttribute<Application, String> key;
    public static volatile SingularAttribute<Application, Account> creator;

}