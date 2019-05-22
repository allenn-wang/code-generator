package com.allenn.generator.generator;

import com.allenn.generator.constants.Constant;
import com.allenn.generator.entity.Table;
import com.allenn.generator.generator.base.AbstractGenerator;
import com.allenn.generator.task.Task;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description  common table generator
 *      include mapper/dao/entity/domain/service/service_impl/controller (configurable)
 * @Author Allenn Wang
 * @Date 2019-05-17
 */
public class CommonGenerator extends AbstractGenerator{

    @Override
    protected LinkedList<Task> generateTask() {
        LinkedList<Task> tasks = new LinkedList<>();
        List<Table> tables = dbHandler.getAllTables();
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
}
