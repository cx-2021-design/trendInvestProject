package org.example.service;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.example.pojo.Index;
import org.example.util.SpringContextUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 作者：cjy
 * 类名：IndexService
 * 全路径类名：org.example.service.IndexService
 * 父类或接口：
 * 描述：指数服务类
 */
@Service
@CacheConfig(cacheNames="indexes")//缓存的名称是 indexes.
public class IndexService {
    private List<Index> indexes;
    @Autowired RestTemplate restTemplate;

    /**
     * 方法名：fresh
     * 传入参数：
     * 异常类型：
     * 返回类型：@return {@link List }<{@link Index }>
     * 描述：刷新数据到Redis，失败就跳到third_part_not_connected断路器保护方法
     */
    @HystrixCommand(fallbackMethod = "third_part_not_connected")
    public List<Index> fresh() {
        indexes =fetch_indexes_from_third_part();//获取数据
        IndexService indexService = SpringContextUtil.getBean(IndexService.class);//获得服务类
        indexService.remove();//删除旧数据
        return indexService.store();//保存新数据
    }

    /**
     * 方法名：remove
     * 传入参数：
     * 异常类型：
     * 返回类型：
     * 描述：清空数据
     */
    @CacheEvict(allEntries=true)
    public void remove(){

    }

    /**
     * 方法名：store
     * 传入参数：
     * 异常类型：
     * 返回类型：@return {@link List }<{@link Index }>
     * 描述：往 redis 里保存数据
     */
    @Cacheable(key="'all_codes'")
    public List<Index> store(){
        System.out.println(this);
        return indexes;
    }

    /**
     * 方法名：get
     * 传入参数：
     * 异常类型：
     * 返回类型：@return {@link List }<{@link Index }>
     * 描述：从 redis 中获取数据
     */
    @Cacheable(key="'all_codes'")
    public List<Index> get(){
        return CollUtil.toList();
    }

    /**
     * 方法名：fetch_indexes_from_third_part
     * 传入参数：
     * 异常类型：
     * 返回类型：@return {@link List }<{@link Index }>
     * 描述：从第三方数据服务获取指数数据
     */
    public List<Index> fetch_indexes_from_third_part(){
        //使用工具类 RestTemplate 来获取地址，获取内容为Map类型
        List<Map> temp= restTemplate.getForObject("http://127.0.0.1:8090/indexes/codes.json",List.class);
        return map2Index(temp);
    }

    /**
     * 方法名：map2Index
     * 传入参数：@param temp 获得的Map数据
     * 异常类型：
     * 返回类型：@return {@link List }<{@link Index }>
     * 描述：把列表的 Map 转换为 Index
     */
    private List<Index> map2Index(List<Map> temp) {
        List<Index> indexes = new ArrayList<>();
        for (Map map : temp) {
            String code = map.get("code").toString();
            String name = map.get("name").toString();
            Index index= new Index();
            index.setCode(code);
            index.setName(name);
            indexes.add(index);
        }

        return indexes;
    }

    /**
     * 方法名：third_part_not_connected
     * 传入参数：
     * 异常类型：
     * 返回类型：@return {@link List }<{@link Index }>
     * 描述：用于获取第三方数据失败时的断路器保护
     */
    public List<Index> third_part_not_connected(){
        System.out.println("third_part_not_connected()");
        Index index= new Index();
        index.setCode("000000");
        index.setName("无效指数代码");
        return CollectionUtil.toList(index);
    }

}