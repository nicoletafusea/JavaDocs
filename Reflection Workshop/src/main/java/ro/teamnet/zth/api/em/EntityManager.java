package ro.teamnet.zth.api.em;

/**
 * Created by user on 7/8/2016.
 */
public interface EntityManager {
    <T> T findById(Class<T> entityClass, Long id);
}
