package org.example.web;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.example.pojo.IndexData;
import org.example.service.IndexDataService;
import org.example.config.IpConfiguration;

/**
 * 作者：cjy
 * 类名：IndexDataController
 * 全路径类名：org.example.web.IndexDataController
 * 父类或接口：
 * 描述：指数数据控制类
 */
@RestController
public class IndexDataController {
    @Autowired IndexDataService indexDataService;
    @Autowired IpConfiguration ipConfiguration;

    @GetMapping("/data/{code}")// 如：http://127.0.0.1:8021/data/000300
    public List<IndexData> get(@PathVariable("code") String code) throws Exception {
        System.out.println("current instance is :" + ipConfiguration.getPort());
        return indexDataService.get(code);
    }
}
