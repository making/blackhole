package am.ik.playground;

import org.junit.Before;
import org.junit.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

public class HelloHandlerTest {

    private WebTestClient testClient;

    @Before
    public void setUp() throws Exception {
        this.testClient = WebTestClient.bindToRouterFunction(new BlackholeHandler().routes()).build();
    }

    @Test
    public void get() throws Exception {
        this.testClient.get()
            .uri("/foo") //
            .exchange() //
            .expectStatus().isOk();
    }


    @Test
    public void post() throws Exception {
        this.testClient.post() //
            .uri("/v1/metrics") //
            .syncBody("{\n" +
                "        \"applications\": [\n" +
                "        {\n" +
                "            \"id\": \"[app_guid]\",\n" +
                "            \"instances\": [\n" +
                "            {\n" +
                "                \"id\": \"[instance_guid]\",\n" +
                "                \"index\": \"[index]\",\n" +
                "                \"metrics\": [\n" +
                "                {\n" +
                "                    \"name\": \"[metric_name]\",\n" +
                "                    \"type\": \"gauge\",\n" +
                "                    \"value\": [metric_value],\n" +
                "                    \"timestamp\": [timestamp],\n" +
                "                    \"unit\": \"[metric_unit]\",\n" +
                "                    \"tags\": {\n" +
                "                        \"[tag_key]\": \"[tag_value]\"\n" +
                "                    }\n" +
                "                }]\n" +
                "            }]\n" +
                "        }]\n" +
                "      }") //
            .exchange() //
            .expectStatus().isAccepted();
    }
}
