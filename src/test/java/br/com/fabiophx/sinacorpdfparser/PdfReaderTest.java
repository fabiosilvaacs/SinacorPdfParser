package br.com.fabiophx.sinacorpdfparser;

import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.junit.jupiter.api.Test;

import static br.com.fabiophx.TestUtils.getPath;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PdfReaderTest {

    @Test
    void shouldThrowInvalidPasswordExceptionWhenPdfPasswordIsIncorrect() {
        assertThrows(
            InvalidPasswordException.class,
            () -> {
                var path = getPath("notas/bmf_1page_senha.pdf");
                PdfReader.getText(path, "wrongPassword");
            }
        );
    }
}
