package com.youotech.securitysystem.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chenzc
 * @date 2017/11/18
 * @time 09:48
 * @desc 统一异常处理
 * @see 1
 */
public class ExceptionHandler implements HandlerExceptionResolver {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    /**
     * 统一异常处理：这里可以处理controller层抛出的异常，但不处理controller捕获的异常
     *
     * @param request
     * @param response
     * @param handler
     * @param ex
     * @return
     */
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        LOGGER.error("异常信息：" + ex);

        MappingJackson2JsonView view = new MappingJackson2JsonView();
        ObjectMapper objectMapper = new ObjectMapper();
        view.setObjectMapper(objectMapper);
        Map<String, Object> map = new HashMap<String, Object>();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setView(view);

        map.put("success", false);
        map.put("msg", ex.getMessage());

        return new ModelAndView(view, map);
    }
}
