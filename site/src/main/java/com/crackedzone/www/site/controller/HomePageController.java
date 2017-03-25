package com.crackedzone.www.site.controller;

import com.crackedzone.www.core.util.HttpResponseUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Package com.crackedzone.www.core.controller
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
@RestController
public class HomePageController {

    @RequestMapping("/")
    public String HomePage() {
        return HttpResponseUtils.success("渲染成功")
                .toString();
    }
}