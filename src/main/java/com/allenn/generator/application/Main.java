package com.allenn.generator.application;

import com.allenn.generator.entity.Configuration;
import com.allenn.generator.generator.BaseGenerator;
import com.allenn.generator.generator.CommonGenerator;
import com.allenn.generator.task.base.Task;
import com.allenn.generator.utils.ConfigUtil;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        Configuration configuration = ConfigUtil.getConfiguration();
        try {
            configuration.vaildConfiguration();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(0);
        }

        ExecutorService executorPool = Executors.newCachedThreadPool();
        LinkedList<Task> allTasks = new LinkedList<>();
        allTasks.addAll(new BaseGenerator().generate());
        allTasks.addAll(new CommonGenerator().generate());
        while (!allTasks.isEmpty()) {
            Task task = allTasks.poll();
            executorPool.execute(() -> {
                try {
                    task.run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executorPool.shutdown();
    }
}
