package com.allenn.generator.generator;

import com.allenn.generator.constants.Constant;
import com.allenn.generator.generator.base.AbstractGenerator;
import com.allenn.generator.task.Task;

import java.util.LinkedList;

/**
 * @Description base code generator, include BaseEntity/BaseService/BaseDao
 * @Author Allenn Wang
 * @Date 2019-05-20
 */
public class BaseGenerator extends AbstractGenerator {
    @Override
    protected LinkedList<Task> generateTask() {
        LinkedList<Task> tasks = new LinkedList<>();
        tasks.add(new Task(Constant.TaskType.BASE_DAO));
        tasks.add(new Task(Constant.TaskType.BASE_ENTITY));
        tasks.add(new Task(Constant.TaskType.BASE_CONTROLLER));
        tasks.add(new Task(Constant.TaskType.BASE_SERVICE));
        tasks.add(new Task(Constant.TaskType.BASE_SERVICE_IMPL));
        return tasks;
    }
}
