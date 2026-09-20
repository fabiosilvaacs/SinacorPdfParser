package br.com.fabiophx.sinacorpdfparser;

import static br.com.fabiophx.TestUtils.getPath;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ParserBMFTest {

	@ParameterizedTest
	@CsvSource({
		"notas/bmf_1page.pdf, 232.16",
		"notas/bmf_1page_loss.pdf, -302.60"
	})
	void showReadNotaBmfAndExtractTotal(String filePath, Double expectedTotal) throws Exception {
		Path pdf = getPath(filePath);

		String texto = new PDFToText(pdf, null).getText();
		List<NotaNegociacao> notas = new ParserBMF().find(texto).getNotas();
		assertEquals(1, notas.size());

		NotaNegociacao nota = assertInstanceOf(NotaNegociacaoBMF.class, notas.getFirst());
		assertEquals(expectedTotal, nota.getTotal());
	}
}
