package com.siworae.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.socket.TextMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class WebSocketController {

    @Bean//这个注解会从Spring容器拿出Bean
    public MyWebSocketHandler infoHandler() {
        return new MyWebSocketHandler();
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView goIndex(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("index");
        return mv;
    }

    @RequestMapping(value = "/login1", method = RequestMethod.GET)
    public ModelAndView index(){
        ModelAndView mv = new ModelAndView();
        mv.setViewName("login");
        return mv;
    }


    @RequestMapping("/login")
    public ModelAndView login(HttpServletRequest request) {
        String username = request.getParameter("username");
        System.out.println(username + "dl");
        HttpSession session = request.getSession();
        session.setAttribute("SESSION_USERNAME", username);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("websocket");
        return mv;
    }

    @RequestMapping("/send")
    @ResponseBody
    public String send(HttpServletRequest request) {
        String username = request.getParameter("username");
        System.out.println("---->"+username);
        infoHandler().sendMessageToUser(username, new TextMessage("你好，欢迎测试！！！！"));
        return null;
    }

}