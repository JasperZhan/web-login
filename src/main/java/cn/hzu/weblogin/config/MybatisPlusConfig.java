package cn.hzu.weblogin.config;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

public class MybatisPlusConfig {
    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/login?serverTimezone=UTC",
                        "root", "123456")
                .globalConfig(builder -> {
                    builder.author("Jasper Zhan") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(System.getProperty("user.dir")+"\\src\\main\\java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("cn.hzu") // 设置父包名
                            .moduleName("weblogin") // 设置父包模块名
                            .entity("model")
                            .service("service")
                            .serviceImpl("service\\impl")
                            .controller("controller")
                            .mapper("dao")
                            .xml("mapper")
                            .pathInfo(Collections.singletonMap(OutputFile.mapperXml, System.getProperty("user.dir")+"\\src\\main\\resources\\mapper")); // 设置mapperXml生成路径
                })
                //, "sys_book", "sys_collection", "sys_review_set", "sys_study_set", "sys_word"
                .strategyConfig(builder -> {
                    builder.addInclude("application_form_info") // 设置需要生成的表名
                            .addTablePrefix("application_") // 设置过滤表前缀
                            .serviceBuilder()
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()
                            .enableLombok()
                            .enableTableFieldAnnotation()
                            .controllerBuilder()
                            .formatFileName("%sController")
                            .enableRestStyle()
                            .mapperBuilder()
                            .superClass(BaseMapper.class)
                            .formatMapperFileName("%sDao")
                            .enableMapperAnnotation()
                            .formatXmlFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
