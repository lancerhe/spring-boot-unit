package com.crackedzone.www.site.controller;

import com.alibaba.fastjson.JSON;
import com.crackedzone.www.core.entity.PageEntity;
import com.crackedzone.www.core.repository.PageRepository;
import com.crackedzone.www.core.util.HttpResponseUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Package com.crackedzone.www.core.controller
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
@RestController
public class HomePageController {

    @Resource
    private PageRepository pageRepository;

    @RequestMapping("/")
    @ResponseBody
    public String HomePage() {
        PageEntity pageEntity = pageRepository.findById(1);
        return HttpResponseUtils.success("渲染成功")
                .put("page", JSON.toJSON(pageEntity))
                .toString();
    }
}