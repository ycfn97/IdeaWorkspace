package read;

import io.searchbox.client.JestClient;
import io.searchbox.client.JestClientFactory;
import io.searchbox.client.config.HttpClientConfig;
import io.searchbox.core.Search;
import io.searchbox.core.SearchResult;

import java.io.IOException;

/**
 * Copyright(c) 2020-2021 sparrow All Rights Reserved
 * Project: gmall-2020
 * Package: read
 * ClassName: EsReader
 *
 * @author 18729 created on date: 2020/11/9 19:21
 * @version 1.0
 * @since JDK 1.8
 */
public class EsReader {
    public static void main(String[] args) throws IOException {
        JestClientFactory jestClientFactory = new JestClientFactory();
        HttpClientConfig build = new HttpClientConfig.Builder("http://hadoop01:9200").build();
        jestClientFactory.setHttpClientConfig(build);
        JestClient object = jestClientFactory.getObject();

        Search build1 = new Search.Builder("{\n" +
                "  \"query\": {\n" +
                "    \"bool\": {\n" +
                "      \"filter\": {\n" +
                "      \"term\": {\n" +
                "        \"class_id\": \"0621\"\n" +
                "      }\n" +
                "      },\n" +
                "      \"must\": [\n" +
                "        {\n" +
                "          \"match\": {\n" +
                "            \"favor2\": \"球\"\n" +
                "          }\n" +
                "        }\n" +
                "      ]\n" +
                "    }\n" +
                "  },\n" +
                "  \"aggs\"  : {\n" +
                "  \"maxAge\": {\n" +
                "  \"max\": {\n" +
                "  \"field\" : \"age\"\n" +
                "      }\n" +
                "    },\n" +
                "    \"countByGender\":{\n" +
                "      \"terms\": {\n" +
                "        \"field\": \"gender\",\n" +
                "        \"size\": 10\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}").addIndex("student").addType("_doc").build();

        SearchResult execute = object.execute(build1);
        System.out.println(execute.getTotal());

        object.shutdownClient();
    }
}
