package br.com.fabiophx.sinacorpdfparser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParserNotaUtils {

    // Retorna um objeto de NotaNegociacao com os dados contidos no .json de mesmo nome do arquivo da nota em .pdf
    public static <T> T readExpectedNota(String pdfResource, Class<T> notaType)
            throws IOException, URISyntaxException {
        String jsonResource = pdfResource.replaceFirst("\\.pdf$", ".json");
        return new ObjectMapper().readValue(getPath(jsonResource).toFile(), notaType);
    }

    public static Path getPath(String resource) throws URISyntaxException {
        var url = ParserNotaUtils.class.getClassLoader().getResource(resource);
        assertNotNull(url, "Recurso não encontrado: " + resource);
        return Path.of(url.toURI());
    }

    public static List<NotaNegociacao> getNotas(String filePath, ParserNota parserNota, String password) throws URISyntaxException, IOException {
        var path = getPath(filePath);
        return getNotas(path, parserNota, password);
    }

    public static List<NotaNegociacao> getNotas(Path path, ParserNota parserNota, String password) throws URISyntaxException, IOException {
        String text = PdfReader.getText(path, password);
        return parserNota.find(text).getNotas();
    }
}
