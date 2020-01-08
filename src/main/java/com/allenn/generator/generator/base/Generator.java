package com.allenn.generator.generator.base;

import com.allenn.generator.task.base.Task;

import java.util.LinkedList;

/**
 * @Description: code generator
 * @Author: allenn wang
 * @Date: 2016-06-22
 */
public interface Generator {
    LinkedList<Task> generate();
}
