package br.com.fabiophx.sinacorpdfparser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.List;

import org.junit.jupiter.api.Test;

class ParserBMFTest {

	@Test
	void deveLerNotaBmfEExtrairTotalLiquidoDaNota() throws Exception {
		Path pdf = resourcePath("notas/20260326_bmf.pdf");

		String texto = new PDFToText(pdf.toString(), null).getText();
		List<NotaNegociacao> notas = new ParserBMF().find(texto).getNotas();

		assertEquals(1, notas.size());
		NotaNegociacaoBMF nota = assertInstanceOf(NotaNegociacaoBMF.class, notas.get(0));
		assertNotNull(nota.getTotalLiquidoDaNota());
		assertEquals(232.16, nota.getTotalLiquidoDaNota(), 0.001);
	}

	private static Path resourcePath(String resource) throws URISyntaxException {
		var url = ParserBMFTest.class.getClassLoader().getResource(resource);
		assertNotNull(url, "Recurso não encontrado: " + resource);
		return Path.of(url.toURI());
	}
}
