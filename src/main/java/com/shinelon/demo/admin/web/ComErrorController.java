package com.shinelon.demo.admin.web;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ComErrorController implements ErrorController {
    private static final Logger logger = LoggerFactory.getLogger(ComErrorController.class);
    private static final String ERROR_PATH = "/error";
    private static final String ERROR_PATH_500 = "/common/500";
    private static final String ERROR_PATH_404 = "/common/404";

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }

    @RequestMapping(value = ERROR_PATH)
    public String handleError(HttpServletResponse response) {
        if (response.getStatus() == 404) {
            return ERROR_PATH_404;
        }
        if (response.getStatus() == 500) {
            return ERROR_PATH_500;
        }
        return ERROR_PATH_500;
    }

    @RequestMapping("/500")
    public String interServerError() throws Exception {
        logger.debug("500");
        return ERROR_PATH_500;
    }

    @RequestMapping("/404")
    public String notFound() {
        logger.debug("404");
        return ERROR_PATH_404;
    }
}
