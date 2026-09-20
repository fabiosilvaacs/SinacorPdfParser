package br.com.fabiophx.sinacorpdfparser;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static br.com.fabiophx.TestUtils.getPath;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParserBovespaTest {

    Parser parser;

    @BeforeAll
    void setup() {
        parser = new ParserBovespa();
    }

    @ParameterizedTest
    @CsvSource({
        "notas/bovespa_1page.pdf, 1086.21",
        "notas/bovespa_1page_debito.pdf, -355.09"
    })
    void showReadNotaBovespaAndExtractTotal(String filePath, Double expectedTotal) throws Exception {
        var path = getPath(filePath);

        String text = PdfReader.getText(path, null);
        List<NotaNegociacao> notas = parser.find(text).getNotas();
        assertEquals(1, notas.size());

        NotaNegociacao nota = assertInstanceOf(NotaNegociacaoBovespa.class, notas.getFirst());
        assertEquals(expectedTotal, nota.getTotal());
    }
}
