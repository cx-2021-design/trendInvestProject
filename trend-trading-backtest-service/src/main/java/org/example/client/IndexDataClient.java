package org.example.client;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.example.pojo.IndexData;

/**
 * 作者：cjy
 * 类名：IndexDataClient
 * 全路径类名：org.example.client.IndexDataClient
 * 父类或接口：
 * 描述：指数数据客户端
 *      使用 feign 模式从 INDEX-DATA-SERVICE 微服务获取数据。
 *      访问不了的时候，就去找 IndexDataClientFeignHystrix 要数据了
 */
@FeignClient(value = "INDEX-DATA-SERVICE",fallback = IndexDataClientFeignHystrix.class)
public interface IndexDataClient {
    @GetMapping("/data/{code}")
    public List<IndexData> getIndexData(@PathVariable("code") String code);
}
