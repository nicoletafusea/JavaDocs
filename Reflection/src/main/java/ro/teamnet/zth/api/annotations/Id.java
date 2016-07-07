package ro.teamnet.zth.api.annotations;


import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import static java.lang.annotation.ElementType.FIELD;
/**
 * Created by user on 7/7/2016.
 */



@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Id {
    String name() default "id";
}

