package com.crackedzone.www.site.controller;

import com.alibaba.fastjson.JSON;
import com.crackedzone.www.core.entity.PageEntity;
import com.crackedzone.www.core.repository.PageRepository;
import com.crackedzone.www.core.util.HttpResponseUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Package com.crackedzone.www.site.controller
 *
 * @author Lancer He <lancer.he@gmail.com>
 */
@RestController
public class PageController {

    @Resource
    private PageRepository pageRepository;

    @RequestMapping(value = "/pages", method = RequestMethod.GET)
    @ResponseBody
    public String getPageList() {
        List<PageEntity> pageEntities = pageRepository.find(1, 10);
        return HttpResponseUtils.success()
                .put("pages", JSON.toJSON(pageEntities))
                .toString();
    }

    @RequestMapping(value = "/pages/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String getPage(@PathVariable int id) {
        PageEntity pageEntity = pageRepository.findById(id);
        return HttpResponseUtils.success()
                .put("page", JSON.toJSON(pageEntity))
                .toString();
    }
}