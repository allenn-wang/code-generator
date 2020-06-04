package com.allenn.generator.generator;

import com.allenn.generator.constants.Constant;
import com.allenn.generator.entity.Column;
import com.allenn.generator.entity.Table;
import com.allenn.generator.generator.base.AbstractGenerator;
import com.allenn.generator.task.EnumTask;
import com.allenn.generator.task.TableTask;
import com.allenn.generator.task.base.Task;
import com.allenn.generator.utils.ConfigUtil;
import com.allenn.generator.utils.StringUtil;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description: base table generator
 * include mapper/dao/entity/domain/service/serviceImpl/controller
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class CommonGenerator extends AbstractGenerator {

    @Override
    protected LinkedList<Task> generateTask() {
        LinkedList<Task> tableTasks = new LinkedList<>();
        List<Table> tables = dbHandler.getAllTables();
//        List<Table> tables = buildTestTable();
        List<String> buildTables = ConfigUtil.getConfiguration().getBuildTables();
        for (Table table : tables) {
            if (buildTables != null && buildTables.contains(table.getName())) {
//                tableTasks.add(new TableTask(Constant.TaskType.CONTROLLER, table));
                tableTasks.add(new TableTask(Constant.TaskType.DAO, table));
                tableTasks.add(new TableTask(Constant.TaskType.ENTITY, table));
//                tableTasks.add(new TableTask(Constant.TaskType.BO, table));
                tableTasks.add(new TableTask(Constant.TaskType.SERVICE, table));
                tableTasks.add(new TableTask(Constant.TaskType.SERVICE_IMPL, table));
                tableTasks.add(new TableTask(Constant.TaskType.MAPPER, table));
                tableTasks.add(new TableTask(Constant.TaskType.COMMON_MAPPER, table));
                for (Column column : table.getColumnList()) {
                    if (Constant.FieldServiceType.ENUM.equals(column.getCommentOption().getDataType())) {
                        tableTasks.add(new EnumTask(table, column));
                    }
                }
            }
        }
        return tableTasks;
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
