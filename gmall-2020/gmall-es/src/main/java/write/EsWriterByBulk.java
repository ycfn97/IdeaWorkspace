package write;

import bean.Movie;
import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Bulk;
import io.searchbox.core.Index;

import java.io.IOException;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: write
 * ClassName: EsWriterByBulk
 *
 * @author 18729 created on date: 2020/11/9 18:47
 * @version 1.0
 * @since JDK 1.8
 */
public class EsWriterByBulk {
    public static void main(String[] args) throws IOException {
        JestClientFactory jestClientFactory = new JestClientFactory();
        HttpClientConfig builder = new HttpClientConfig.Builder("http://hadoop01:9200").build();
        jestClientFactory.setHttpClientConfig(builder);
        JestClient object = jestClientFactory.getObject();

        Movie sunqi = new Movie("1003", "sunqi");
        Movie aaaa = new Movie("1004", "aaaa");

        Index id = new Index.Builder(sunqi).id("1003").build();
        Index build = new Index.Builder(aaaa).id("1004").build();

        Bulk build1 = new Bulk.Builder().defaultIndex("movie_test3").defaultType("_doc").build();

        object.execute(build1);

        object.shutdownClient();
    }
}
