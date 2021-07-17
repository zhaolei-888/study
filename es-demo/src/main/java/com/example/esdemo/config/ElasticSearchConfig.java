package com.example.esdemo.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhaolei
 * @E-mail zhaolei@dazhuanjia.com
 * @date 2021/4/26 6:12 下午 周一
 */
@Configuration
public class ElasticSearchConfig {

    @Bean
    public RestHighLevelClient restHighLevelClient(){
        RestHighLevelClient client = new RestHighLevelClient(
                //如果是集群就构建多个es客户端，
                RestClient.builder(
//                        new HttpHost("localhost", 9200, "http"),
                        new HttpHost("127.0.0.1", 9200, "http")));
        return client;
    }
}
