package com.shinelon.demo.admin.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shinelon.demo.admin.mapper.TestMapper;

/***
 *
 * @author syq
 *
 */
@Controller
public class TestController {
    private static final Logger logger = LoggerFactory.getLogger(TestController.class);
    @Autowired
    private TestMapper testMapper;

    @RequestMapping("/test")
    @ResponseBody
    public String test() {
        logger.debug("test mybatisplus...");
        return testMapper.selectList(null).toString();
    }

}
