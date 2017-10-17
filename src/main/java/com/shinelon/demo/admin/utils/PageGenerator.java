package com.shinelon.demo.admin.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baomidou.mybatisplus.generator.config.ConstVal;

/***
 *
 * @author syq
 *
 */
public class PageGenerator {
    private static final Logger logger = LoggerFactory.getLogger(PageGenerator.class);

    public static void main(String[] args) throws Exception {
        String templatePath = "/templates/htmlvm/page.html.vm";
        String outputFile = "D://mptest//demo.html";
        PageGenerator pg = new PageGenerator();
        Map<String, String> parms = new HashMap<>(5);
        parms.put("title", "demo");
        pg.generatorPage(parms, outputFile, templatePath);
    }

    private VelocityEngine engine;

    private void generatorPage(Map<String, String> parms, String outputFile, String templatePath) throws Exception {
        engine = getVelocityEngine();
        Template template = engine.getTemplate("/templates/htmlvm/page.html.vm");
        VelocityContext ctx = new VelocityContext();
        parms.forEach((k, v) -> {
            ctx.put(k, v);
        });

        File file = new File(outputFile);
        if (!file.getParentFile().exists()) {
            // 如果文件所在的目录不存在，则创建目录
            if (!file.getParentFile().mkdirs()) {
                logger.debug("创建文件所在的目录失败!");
                return;
            }
        }
        FileOutputStream fos = new FileOutputStream(outputFile);
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, ConstVal.UTF8));
        template.merge(ctx, writer);
        writer.close();
        logger.debug("模板:" + templatePath + ";  文件:" + outputFile);
    }

    private VelocityEngine getVelocityEngine() {
        if (engine == null) {
            Properties p = new Properties();
            p.setProperty(ConstVal.VM_LOADPATH_KEY, ConstVal.VM_LOADPATH_VALUE);
            p.setProperty(Velocity.FILE_RESOURCE_LOADER_PATH, "");
            p.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
            p.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
            p.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
            p.setProperty("file.resource.loader.unicode", "true");
            engine = new VelocityEngine(p);
            engine.init(p);
        }
        return engine;
    }
}
