package com.allenn.generator.generator.base;

import com.allenn.generator.task.Task;

import java.util.LinkedList;

/**
 * @Description code generator
 * @Author Allenn Wang
 * @Date 2019-05-17
 */
public interface Generator {
    LinkedList<Task> generate();
}
