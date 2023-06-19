package org.example.web;

import java.util.List;
import org.example.pojo.Index;
import org.example.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作者：cjy
 * 类名：IndexController
 * 全路径类名：org.example.web.IndexController
 * 父类或接口：
 * 描述：指数控制器
 *///访问 getCodes的时候，
@RestController
public class IndexController {
    @Autowired IndexService indexService;

//  http://127.0.0.1:8001/freshCodes
//  http://127.0.0.1:8001/getCodes
//  http://127.0.0.1:8001/removeCodes

    @GetMapping("/freshCodes")
    public List<Index> fresh() throws Exception {
        return indexService.fresh();
    }

    @GetMapping("/getCodes")
    public List<Index> get() throws Exception {
        return indexService.get();
    }
    @GetMapping("/removeCodes")
    public String remove() throws Exception {
        indexService.remove();
        return "remove codes successfully";
    }
}