package com.allenn.generator.generator;

import com.allenn.generator.constants.Constant;
import com.allenn.generator.generator.base.AbstractGenerator;
import com.allenn.generator.task.TableTask;
import com.allenn.generator.task.base.Task;

import java.util.LinkedList;

/**
 * @Description: base code generator, include BaseEntity/BaseService/BaseServiceImpl/BaseDao/BaseController
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public class BaseGenerator extends AbstractGenerator {
    @Override
    protected LinkedList<Task> generateTask() {
        LinkedList<Task> tableTasks = new LinkedList<>();
        tableTasks.add(new TableTask(Constant.TaskType.EXCEPTION));
        tableTasks.add(new TableTask(Constant.TaskType.EXCEPTION_HANDLER));
        tableTasks.add(new TableTask(Constant.TaskType.I_RESULT_CODE));
        tableTasks.add(new TableTask(Constant.TaskType.RESULT_CODE));
        tableTasks.add(new TableTask(Constant.TaskType.I_ENUM));
        tableTasks.add(new TableTask(Constant.TaskType.RESULT_OBJECT));
        tableTasks.add(new TableTask(Constant.TaskType.QUERY_OBJECT));
        tableTasks.add(new TableTask(Constant.TaskType.QUERY_CONDITION));
        tableTasks.add(new TableTask(Constant.TaskType.PAGE_OBJECT));
        tableTasks.add(new TableTask(Constant.TaskType.BASE_DAO));
        tableTasks.add(new TableTask(Constant.TaskType.BASE_ENTITY));
        tableTasks.add(new TableTask(Constant.TaskType.BASE_POJO));
        tableTasks.add(new TableTask(Constant.TaskType.BASE_CONTROLLER));
        tableTasks.add(new TableTask(Constant.TaskType.BASE_SERVICE));
        tableTasks.add(new TableTask(Constant.TaskType.BASE_SERVICE_IMPL));
        return tableTasks;
    }
}
