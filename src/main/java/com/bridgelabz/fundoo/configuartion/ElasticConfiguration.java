package com.bridgelabz.fundoo.configuartion;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * Purpose:ElasticSearcch Configuration Class 
 * @author Rohan Kadam
 *
 */
@Configuration
public class ElasticConfiguration {
    /**
     * Purpose: To provide load balancing along nodes
     * @return client 
     */
    @Bean
    public RestHighLevelClient client() {
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost("localhost", 9200, "http")).setMaxRetryTimeoutMillis(6000));
        return client;
    }
}
