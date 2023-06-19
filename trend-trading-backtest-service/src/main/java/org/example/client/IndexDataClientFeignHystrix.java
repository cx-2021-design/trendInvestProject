package org.example.client;

import java.util.List;

import org.springframework.stereotype.Component;

import org.example.pojo.IndexData;
import cn.hutool.core.collection.CollectionUtil;

/**
 * 作者：cjy
 * 类名：IndexDataClientFeignHystrix
 * 全路径类名：org.example.client.IndexDataClientFeignHystrix
 * 父类或接口：@see IndexDataClient
 * 描述：实现了 IndexDataClient，所以就提供了对应的方法，当熔断发生的时候，对应的方法就会被调用
 */
@Component
public class IndexDataClientFeignHystrix implements IndexDataClient {

    @Override
    public List<IndexData> getIndexData(String code) {
        IndexData indexData = new IndexData();
        indexData.setClosePoint(0);
        indexData.setDate("0000-00-00");
        return CollectionUtil.toList(indexData);
    }

}
