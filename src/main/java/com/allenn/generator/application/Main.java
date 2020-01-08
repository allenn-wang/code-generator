package com.allenn.generator.application;

import com.allenn.generator.entity.Configuration;
import com.allenn.generator.generator.BaseGenerator;
import com.allenn.generator.generator.CommonGenerator;
import com.allenn.generator.utils.ConfigUtil;
import com.allenn.generator.task.base.Task;

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
        LinkedList<Task> allTableTasks = new LinkedList<>();
        allTableTasks.addAll(new BaseGenerator().generate());
        allTableTasks.addAll(new CommonGenerator().generate());
        while (!allTableTasks.isEmpty()) {
            Task task = allTableTasks.poll();
            executorPool.execute(() -> {
                try {
                    task.exec();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        executorPool.shutdown();
    }
}
