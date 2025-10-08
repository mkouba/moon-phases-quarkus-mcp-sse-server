package astro.moon;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Map;

import org.junit.jupiter.api.Test;

import io.quarkiverse.mcp.server.JsonRpcErrorCodes;
import io.quarkiverse.mcp.server.TextContent;
import io.quarkiverse.mcp.server.test.McpAssured;
import io.quarkiverse.mcp.server.test.McpAssured.McpStreamableTestClient;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class MoonPhaseMcpServerTest {

    @Test
    public void testMcpServer() {
        McpStreamableTestClient client = McpAssured.newConnectedStreamableClient();
        client.when()
                .toolsCall("moon-phase-at-date",
                        Map.of("moonPhaseRequest", new MoonPhaseRequest(LocalDate.of(1982, 10, 28))), r -> {
                            TextContent result = r.content().get(0).asText();
                            assertEquals("{\"phase\":\"waxing gibbous\",\"emoji\":\"🌔\"}", result.text());
                        })
                .toolsCall("moon-phase-at-date")
                .withArguments(Map.of("moonPhaseRequest", new MoonPhaseRequest(null)))
                .withErrorAssert(error -> {
                    assertEquals(JsonRpcErrorCodes.INVALID_PARAMS, error.code());
                    assertEquals("moonPhaseAtDate.moonPhaseRequest.date: must not be null", error.message());
                })
                .send()
                .thenAssertResults();
    }

}
