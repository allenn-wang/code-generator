package com.allenn.generator.generator.base;

import com.allenn.generator.db.DBFactory;
import com.allenn.generator.db.base.DBHandler;
import com.allenn.generator.task.Task;
import com.allenn.generator.utils.ConfigUtil;

import java.util.LinkedList;

/**
 * @Description do some general operations
 * @Author Allenn Wang
 * @Date 2019-05-20
 */
public abstract class AbstractGenerator implements Generator {
    protected DBHandler dbHandler;

    @Override
    public LinkedList<Task> generate() {
        init();
        return generateTask();
    }

    private void init() {
        this.dbHandler = DBFactory.getDBHandler(ConfigUtil.getConfiguration().getDb());
    }

    protected abstract LinkedList<Task> generateTask();
}
