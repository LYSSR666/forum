package com.ly.forum.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

public class CodeGenerator {

    public static void main(String[] args) {
        /* 构建一个代码生成器对象 */
        AutoGenerator mpg = new AutoGenerator();
        /* 配置策略 */
        //1、全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/src/main/java");
        gc.setAuthor("李阳");
        gc.setOpen(false);
        //是否覆盖之前生成的文件夹或文件
        gc.setFileOverride(false);
        // 去掉Service的I前缀
        gc.setServiceName("%sService");
        gc.setIdType(IdType.ID_WORKER);
        gc.setDateType(DateType.ONLY_DATE);
//        gc.setSwagger2(true);
        /* 设置全局配置 */
        mpg.setGlobalConfig(gc);

        /* 设置数据源 */
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://localhost:3306/forum?useUnicode=true&characterEncoding=utf-8&severTimezone=UTC");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        dsc.setDbType(DbType.MYSQL);
        mpg.setDataSource(dsc);

        /* 包的配置 */
        PackageConfig pc = new PackageConfig();
        pc.setModuleName("comment");
        pc.setParent("com.ly.forum");
        pc.setEntity("pojo");
        pc.setMapper("mapper");
        pc.setService("service");
        pc.setController("controller");
        mpg.setPackageInfo(pc);

        /* 策略配置 */
        StrategyConfig strategy = new StrategyConfig();
        //设置要映射的表名
        strategy.setInclude("comment");
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //自动设置设置Lombok
        strategy.setEntityLombokModel(true);
        //设置Controller层
        strategy.setRestControllerStyle(true);
        strategy.setControllerMappingHyphenStyle(true);
        mpg.setStrategy(strategy);
        /* 执行代码生成器 */
        mpg.execute();
    }

}
