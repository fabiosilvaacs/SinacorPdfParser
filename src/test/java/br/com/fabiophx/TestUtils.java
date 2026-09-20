package br.com.fabiophx;

import java.net.URISyntaxException;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TestUtils {

    public static Path getPath(String resource) throws URISyntaxException {
        var url = TestUtils.class.getClassLoader().getResource(resource);
        assertNotNull(url, "Recurso não encontrado: " + resource);

        return Path.of(url.toURI());
    }
}
