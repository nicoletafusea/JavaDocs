package ro.teamnet.zth.api.annotations;

import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.TYPE;


/**
 * Created by user on 7/7/2016.
 */

@Target(TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String name() default "";
}

