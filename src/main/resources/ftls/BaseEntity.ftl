package ${basePackageName}.${entityPackageName}.base;

/**
* @author：${author} <br/>
* @date：${date} <br/>
* @description：base entity, default primaryKey
*/
public interface Base${entityClassName} extends Serializable {
    public void set_Id(Object id);

    public Object get_Id();
}