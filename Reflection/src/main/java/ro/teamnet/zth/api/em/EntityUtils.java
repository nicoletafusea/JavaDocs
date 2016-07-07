package ro.teamnet.zth.api.em;
import ro.teamnet.zth.api.annotations.Column;
import ro.teamnet.zth.api.annotations.Id;
import ro.teamnet.zth.api.annotations.Table;

import java.lang.Class;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
/**
 * Created by user on 7/7/2016.
 */
public class EntityUtils {

    private EntityUtils() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    public static String getTableName(Class entity) {
        return entity.getAnnotation(Table.class).toString();
    }

    public static List<ColumnInfo> getColumns(Class entity) {
        List<ColumnInfo> list = new ArrayList<>();
        Field[] fields = new Field[100];
        fields = entity.getDeclaredFields();

        for (int i = 0 ; i < fields.length ; i++) {
            ColumnInfo colInfo = new ColumnInfo();
            colInfo.setColumnName(fields[i].getName());
            colInfo.setColumnType(fields[i].getType());

            Column c = (Column)entity.getAnnotation(Column.class);
            Id id = (Id)entity.getAnnotation(Id.class);

            if (c == null) {
                colInfo.setDbName(id.name());
                colInfo.setId(true);
            } else {
                colInfo.setDbName(c.name());
                colInfo.setId(false);
            }
            list.add(colInfo);
        }

        return list;
    }

    public static Object castFromSqlType(Object value, Class wantedType) {
        if (value.getClass().equals(BigDecimal.class) && wantedType.getClass().equals(Integer.class)) {
            return (Integer)value;
        } else if (value.getClass().equals(BigDecimal.class) && wantedType.getClass().equals(Long.class)) {
            return (Long)value;
        } else if (value.getClass().equals(BigDecimal.class) && wantedType.getClass().equals(Float.class)) {
            return (Float)value;
        } else if (value.getClass().equals(BigDecimal.class) && wantedType.getClass().equals(Double.class)) {
            return (Double)value;
        } else {
            return value;
        }
    }

    public static List<Field> getFieldsByAnnotations(Class clazz, Class annotation) {
        List<Field> list = new ArrayList<Field>();
        Field[] fields = clazz.getDeclaredFields();

        for (int i = 0 ; i < fields.length ; i++) {
            if (fields[i].getAnnotation(annotation) != null)
                list.add(fields[i]);
        }

        return list;
    }

    public static Object getSqlValue(Object object) throws IllegalAccessException {
        if(object.getClass().getAnnotation(Table.class)!=null){
            Field idField = getFieldsByAnnotations(object.getClass(), Id.class).get(0);
            idField.setAccessible(true);
            return idField.get(object);
        }
        return object;
    }
}
