package org.example.service;

import java.util.List;

import org.example.pojo.Index;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import cn.hutool.core.collection.CollUtil;

/**
 * 作者：cjy
 * 类名：IndexService
 * 全路径类名：org.example.service.IndexService
 * 父类或接口：
 * 描述：指数服务类，直接从reids获取数据。 如果没有数据，则会返回 “无效指数代码”。
 */
@Service
@CacheConfig(cacheNames="indexes")
public class IndexService {
    private List<Index> indexes;

    /**
     * 方法名：get
     * 传入参数：
     * 异常类型：
     * 返回类型：@return {@link List }<{@link Index }>
     * 描述：得到
     */
    @Cacheable(key="'all_codes'")
    public List<Index> get(){
        Index index = new Index();
        index.setName("无效指数代码");
        index.setCode("000000");
        return CollUtil.toList(index);
    }
}
