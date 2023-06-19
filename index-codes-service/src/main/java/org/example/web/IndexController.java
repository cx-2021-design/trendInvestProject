package org.example.web;

import java.util.List;
import org.example.config.IpConfiguration;
import org.example.pojo.Index;
import org.example.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 作者：cjy
 * 类名：IndexController
 * 全路径类名：org.example.web.IndexController
 * 父类或接口：
 * 描述：指数控制类
 */
@RestController
public class IndexController {
    @Autowired
    IndexService indexService;
    @Autowired
    IpConfiguration ipConfiguration;

    /**
     * 方法名：codes
     * 传入参数：
     * 异常类型：@throws Exception 异常
     * 返回类型：@return {@link List }<{@link Index }>
     * 描述：获得指数
     */
    @GetMapping("/codes")//http://127.0.0.1:8011/codes
    @CrossOrigin//@CrossOrigin 表示允许跨域，因为后续的回测视图是另一个端口号的，访问这个服务是属于跨域了
    public List<Index> codes() throws Exception {

        //通过 IpConfiguration 获取当前接口并打印。
        System.out.println("current instance's port is "+ ipConfiguration.getPort());
        return indexService.get();
    }
}
