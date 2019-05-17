package com.allenn.generator.invoker.base;

import com.allenn.generator.task.base.Task;

import java.util.List;

/**
 * @Description
 * @Author Allenn Wang
 * @Date 2019-05-17
 */
public interface Generator {
    List<Task> generate();
}
