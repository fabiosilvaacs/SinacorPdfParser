package br.com.fabiophx.sinacorpdfparser;

import static br.com.fabiophx.TestUtils.getPath;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ParserBMFTest {

	Parser parser;

	@BeforeAll
	void setup() {
		parser = new ParserBMF();
	}

	@ParameterizedTest
	@CsvSource({
		"notas/bmf_1page.pdf, 232.16",
		"notas/bmf_1page_loss.pdf, -302.60",
	})
	void showReadNotaBmfAndExtractTotal(String filePath, Double expectedTotal) throws Exception {
		List<NotaNegociacao> notas = getNotas(filePath);
		assertEquals(1, notas.size());

		NotaNegociacao nota = assertInstanceOf(NotaNegociacaoBMF.class, notas.getFirst());
		assertEquals(expectedTotal, nota.getTotal());
	}

	@Test
	void shouldThrowArrayIndexOutOfBoundsException() {
		assertThrows(
			ArrayIndexOutOfBoundsException.class,
			() -> getNotas("notas/bmf_2pages.pdf")
		);
	}

	private List<NotaNegociacao> getNotas(String filePath) throws URISyntaxException, IOException {
		var path = getPath(filePath);
		String text = PdfReader.getText(path, null);
		return parser.find(text).getNotas();
	}
}
