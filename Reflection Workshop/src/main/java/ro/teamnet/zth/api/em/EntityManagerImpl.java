package ro.teamnet.zth.api.em;

import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.database.DBManager;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by user on 7/8/2016.
 */
public class EntityManagerImpl implements EntityManager {
    @Override
    public <T> T findById(Class<T> entityClass, Long id) {
        Connection c = DBManager.getConnection();
        QueryBuilder queryBuilder = new QueryBuilder();
        String query;

        String tableName = EntityUtils.getTableName(entityClass);
        List<ColumnInfo> columns = EntityUtils.getColumns(entityClass);
        List<Field> fields = EntityUtils.getFieldsByAnnotations(entityClass, Id.class);

        String fieldName = ((Id)fields.get(0).getDeclaredAnnotation(Id.class)).name();
        System.out.println(fields);
        Condition cond = new Condition(fieldName, id);

        queryBuilder.setTableName(tableName);
        queryBuilder.setQueryType(QueryType.SELECT);
        queryBuilder.addQueryColumns(columns);
        queryBuilder.addCondition(cond);

        query = queryBuilder.createQuery();
        System.out.println(query);

        Statement stmt = null;
        try {
            stmt = c.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            T elem = entityClass.newInstance();
            while (resultSet.next()) {
                Field f;
                for (ColumnInfo col : columns) {
                    f = entityClass.getDeclaredField(col.getColumnName());
                    f.setAccessible(true);
                    f.set(elem, EntityUtils.castFromSqlType(resultSet.getObject(col.getDbName()), f.getType()));
                }
            }
            return elem;
        } catch (Exception e) {
            System.out.println("cannot get the value");
            e.printStackTrace();
            return null;
        }
    }
}
