package write;

import bean.Movie;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Index;

import java.io.IOException;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: write
 * ClassName: EsWriter
 *
 * @author 18729 created on date: 2020/11/9 14:50
 * @version 1.0
 * @since JDK 1.8
 */
public class EsWriter {
    public static void main(String[] args) throws IOException {
//        创建工厂，设置连接属性，获取客户端对象，准备数据，写入数据，
        JestClientFactory jestClientFactory = new JestClientFactory();
        HttpClientConfig build = new HttpClientConfig.Builder("http://hadoop01:9200").build();
        jestClientFactory.setHttpClientConfig(build);
        JestClient object = jestClientFactory.getObject();
        Movie movie = new Movie("1001","拜登");
        Index doc = new Index.Builder(movie).index("movie_test3").type("_doc").id("1001").build();
        object.execute(doc);
        object.shutdownClient();
    }
}
