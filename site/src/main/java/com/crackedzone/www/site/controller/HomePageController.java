package com.crackedzone.www.site.controller;

import com.crackedzone.www.core.entity.ManagerEntity;
import com.crackedzone.www.core.repository.ManagerRepository;
import com.crackedzone.www.core.util.HttpResponseUtils;
import com.crackedzone.www.core.util.MD5Utils;
import com.crackedzone.www.site.WebSecurityConfig;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

/**
 * Package com.crackedzone.www.core.controller
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
@RestController
public class HomePageController {

    @Resource
    private ManagerRepository managerRepository;

    @RequestMapping("/")
    public String HomePage() {
        return HttpResponseUtils.success("渲染成功")
                .toString();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String Login(HttpSession session, @RequestParam("username") String username, @RequestParam("password") String password) throws NoSuchAlgorithmException {
        String        passwordEncrypt = MD5Utils.encrypt(password);
        ManagerEntity entity          = managerRepository.findByUsername(username);
        if (!entity.getPassword().equals(passwordEncrypt))
            throw new RuntimeException("Login failed.");

        session.setAttribute(WebSecurityConfig.SESSION_KEY, username);
        return HttpResponseUtils.success().toString();
    }
}