package org.example.service;

import org.example.pojo.IndexData;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import cn.hutool.core.collection.CollUtil;

import java.util.List;

/**
 * 作者：cjy
 * 类名：IndexDataService
 * 全路径类名：org.example.service.IndexDataService
 * 父类或接口：
 * 描述：从Redis缓存中获得指数数据
 */
@Service
@CacheConfig(cacheNames="index_datas")
public class IndexDataService {

    @Cacheable(key="'indexData-code-'+ #p0")
    public List<IndexData> get(String code){
        return CollUtil.toList();
    }
}