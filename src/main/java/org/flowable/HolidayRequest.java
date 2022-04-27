package org.flowable;

import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;

public class HolidayRequest {
    public static void main(String[] args) {

        //这里改用mysql，注意后面的nullCatalogMeansCurrent=true
        //注意，pom需要添加mysql驱动依赖
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:mysql://localhost:3306/flowable_official?useUnicode=true" +
                        "&characterEncoding=utf-8&serverTimezone=Asia/Shanghai&allowMultiQueries=true"
                        +"&nullCatalogMeansCurrent=true"
                )
                .setJdbcUsername("root")
                .setJdbcPassword("123456")
                .setJdbcDriver("com.mysql.cj.jdbc.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
        /* //这是官网，用的h2
        ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration()
                .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
                .setJdbcUsername("sa")
                .setJdbcPassword("")
                .setJdbcDriver("org.h2.Driver")
                .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);*/

        ProcessEngine processEngine = cfg.buildProcessEngine();
    }
}
