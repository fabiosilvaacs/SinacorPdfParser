package br.com.fabiophx.sinacorpdfparser;

import java.math.BigDecimal;

import static br.com.fabiophx.sinacorpdfparser.ParserNotaUtils.getNotas;
import static br.com.fabiophx.sinacorpdfparser.ParserNotaUtils.readExpectedNota;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParserNotaBMFTest {

	ParserNota parserNota;
	Class<NotaNegociacaoBMF> notaClass;

	@BeforeAll
	void setup() {
		parserNota = new ParserNotaBMF();
		notaClass = NotaNegociacaoBMF.class;
	}

	@ParameterizedTest
	@ValueSource(strings = {
		"notas/bmf_1page.pdf",
		"notas/bmf_1page_loss.pdf",
	})
	void shouldReadNotaBmfAndCompareExpectedFields(String filePath) throws Exception {
		List<NotaNegociacao> notas = getNotas(filePath, parserNota, null);
		assertEquals(1, notas.size());

		NotaNegociacaoBMF nota = assertInstanceOf(notaClass, notas.getFirst());
		var expected = readExpectedNota(filePath, notaClass);

		assertThat(nota)
			.usingRecursiveComparison()
			.ignoringExpectedNullFields()
			.withComparatorForType(BigDecimal::compareTo, BigDecimal.class)
			.isEqualTo(expected);
	}

	@Test
	void shouldThrowArrayIndexOutOfBoundsException() {
		assertThrows(
			ArrayIndexOutOfBoundsException.class,
			() -> getNotas("notas/bmf_2pages.pdf", parserNota, null)
		);
	}
}
