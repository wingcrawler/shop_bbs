package com.sqe.shop.controller.ue;

import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.baidu.ueditor.ActionEnter;

/**
 * 用于处理关于ueditor插件相关的请求
 * @author Guoqing
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/ue/ueditor")
public class UeditorController {

    @RequestMapping(value = "/exec")
    @ResponseBody
    public String exec(HttpServletRequest request) throws UnsupportedEncodingException{ 
        request.setCharacterEncoding("utf-8");
        String rootPath = request.getRealPath("/");
        return new ActionEnter( request, rootPath).exec();
    }
}