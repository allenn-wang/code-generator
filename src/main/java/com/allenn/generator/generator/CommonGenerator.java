package com.allenn.generator.generator;

import com.allenn.generator.constants.Constant;
import com.allenn.generator.entity.Table;
import com.allenn.generator.generator.base.AbstractGenerator;
import com.allenn.generator.task.base.Task;

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
            tasks.add(generateTaskByType(Constant.TaskType.CONTROLLER));
            tasks.add(generateTaskByType(Constant.TaskType.DAO));
            tasks.add(generateTaskByType(Constant.TaskType.DOMAIN));
            tasks.add(generateTaskByType(Constant.TaskType.ENTITY));
            tasks.add(generateTaskByType(Constant.TaskType.SERVICE));
            tasks.add(generateTaskByType(Constant.TaskType.SERVICE_IMPL));
            tasks.add(generateTaskByType(Constant.TaskType.MAPPER));
        }
        return tasks;
    }
}
