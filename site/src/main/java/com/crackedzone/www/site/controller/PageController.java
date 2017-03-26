package com.crackedzone.www.site.controller;

import com.alibaba.fastjson.JSON;
import com.crackedzone.www.core.entity.PageEntity;
import com.crackedzone.www.core.repository.PageRepository;
import com.crackedzone.www.core.HttpResponse;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
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

    @RequestMapping(value = "/pages", method = RequestMethod.GET, produces = "application/json")
    public String getPageList() {
        List<PageEntity> pageEntities = pageRepository.find(1, 10);
        return HttpResponse.success()
                .put("pages", JSON.toJSON(pageEntities))
                .toString();
    }

    @RequestMapping(value = "/pages/{id}", method = RequestMethod.GET, produces = "application/json")
    public String getPage(@PathVariable Integer id) {
        PageEntity pageEntity = pageRepository.findById(id);
        return HttpResponse.success()
                .put("page", JSON.toJSON(pageEntity))
                .toString();
    }

    @RequestMapping(value = "/pages/create", method = RequestMethod.POST, produces = "application/json")
    public String createPage(
            @RequestParam(value = "title") String title,
            @RequestParam(value = "cname") String cname,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "publish_date") String publishDate
    ) {
        PageEntity pageEntity = new PageEntity();
        pageEntity.setCname(cname);
        pageEntity.setTitle(title);
        pageEntity.setContent(content);
        pageEntity.setPublishDate(Date.valueOf(publishDate));
        pageRepository.create(pageEntity);
        return HttpResponse.success().toString();
    }

    @RequestMapping(value = "/pages/save", method = RequestMethod.POST, produces = "application/json")
    public String savePage(
            @RequestParam(value = "id") Integer id,
            @RequestParam(value = "title") String title,
            @RequestParam(value = "cname") String cname,
            @RequestParam(value = "content") String content,
            @RequestParam(value = "publish_date") String publishDate
    ) {
        PageEntity pageEntity = pageRepository.findById(id);
        pageEntity.setCname(cname);
        pageEntity.setTitle(title);
        pageEntity.setContent(content);
        pageEntity.setPublishDate(Date.valueOf(publishDate));
        pageRepository.save(pageEntity);
        return HttpResponse.success().toString();
    }
}