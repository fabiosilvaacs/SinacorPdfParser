package br.com.fabiophx.sinacorpdfparser;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static br.com.fabiophx.sinacorpdfparser.ParserNotaUtils.getNotas;
import static br.com.fabiophx.sinacorpdfparser.ParserNotaUtils.readExpectedNota;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

// @Disabled("Desabilitado pois necessita de arquivos de nota em .pdf e seu respectivo json com os valores esperados")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ParserNotaBovespaTest {

    ParserNota parserNota;
    Class<NotaNegociacaoBovespa> notaClass;

    @BeforeAll
    void setup() {
        parserNota = new ParserNotaBovespa();
        notaClass = NotaNegociacaoBovespa.class;
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "notas/bovespa_1page.pdf",
        "notas/bovespa_1page_debito.pdf"
    })
    void shouldReadNotaBovespaAndCompareExpectedFields(String filePath) throws Exception {
        List<NotaNegociacao> notas = getNotas(filePath, parserNota, null);
        assertEquals(1, notas.size());

        NotaNegociacaoBovespa nota = assertInstanceOf(notaClass, notas.getFirst());
        var expected = readExpectedNota(filePath, notaClass);

        assertThat(nota)
            .usingRecursiveComparison()
            .ignoringExpectedNullFields()
            .withComparatorForType(BigDecimal::compareTo, BigDecimal.class)
            .isEqualTo(expected);
    }
}
