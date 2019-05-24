package com.allenn.generator.generator;

import com.allenn.generator.constants.Constant;
import com.allenn.generator.entity.Column;
import com.allenn.generator.entity.Table;
import com.allenn.generator.generator.base.AbstractGenerator;
import com.allenn.generator.task.Task;
import com.allenn.generator.utils.StringUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description  common table generator
 *      include mapper/dao/entity/domain/service/serviceImpl/controller
 * @Author Allenn Wang
 * @Date 2019-05-17
 */
public class CommonGenerator extends AbstractGenerator{

    @Override
    protected LinkedList<Task> generateTask() {
        LinkedList<Task> tasks = new LinkedList<>();
//        List<Table> tables = dbHandler.getAllTables();
        List<Table> tables = buildTestTable();
        for (Table table : tables) {
            tasks.add(new Task(Constant.TaskType.CONTROLLER, table));
            tasks.add(new Task(Constant.TaskType.DAO, table));
            tasks.add(new Task(Constant.TaskType.DTO, table));
            tasks.add(new Task(Constant.TaskType.ENTITY, table));
            tasks.add(new Task(Constant.TaskType.SERVICE, table));
            tasks.add(new Task(Constant.TaskType.SERVICE_IMPL, table));
            tasks.add(new Task(Constant.TaskType.MAPPER, table));
        }
        return tasks;
    }

    // For simple test
    private List<Table> buildTestTable() {
        List<Table> tables = new LinkedList<>();
        Table table = new Table();
        table.setName("system_user");
        table.setAnnotation("test table");
        table.setClassName(StringUtil.dbName2JavaName(table.getName()));

        List<Column> columns = new LinkedList<>();
        Column column = new Column();
        column.setName("user_id");
        column.setAnnotation("pk id");
        column.setJdbcType("VARCHAR");
        column.setNullAble(false);
        column.setPrimarykey(true);
        column.setLength(50);
        column.setJavaType("String");
        column.setPropertyName(StringUtil.lowerFirstChar(StringUtil.dbName2JavaName(column.getName())));
        columns.add(column);
        table.setPrimaryKeyColumn(column);

        Column column1 = new Column();
        column1.setName("user_name");
        column1.setAnnotation("user name");
        column1.setJdbcType("VARCHAR");
        column1.setNullAble(false);
        column1.setPrimarykey(false);
        column1.setLength(50);
        column1.setJavaType("String");
        column1.setPropertyName(StringUtil.lowerFirstChar(StringUtil.dbName2JavaName(column1.getName())));
        columns.add(column1);
        table.setColumnList(columns);

        tables.add(table);
        return tables;
    }
}
