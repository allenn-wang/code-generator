package com.allenn.generator.task.base;

import com.allenn.generator.entity.Table;

/**
 * @Description
 * @Author Allenn Wang
 * @Date 2019-05-17
 */
public abstract class Task {
    protected Table table;

    public Task() {
    }

    public Task(Table table) {
        this.table = table;
    }

    public void run() {
//        Map<String, Object> model = buildModel();
//        model.put(Constant.FtlTag.AUTHOR, ConfigUtil.getConfiguration().getAuthor());
//        model.put(Constant.FtlTag.DATE, LocalDate.now());
//
//        String packageName = String.valueOf(model.get(Constant.FtlTag.PACKAGE_NAME));
//        if (!StringUtils.isEmpty(packageName)) {
//
//        }
    }

    //protected abstract Map<String, Object> buildModel();
}
