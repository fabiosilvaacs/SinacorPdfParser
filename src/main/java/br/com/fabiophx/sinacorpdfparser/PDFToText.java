package br.com.fabiophx.sinacorpdfparser;

import java.io.IOException;
import java.nio.file.Path;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PDFToText {
	
	private PDDocument document;

	public PDFToText(String filePath, String password) throws IOException {
		document = PDDocument.load(Path.of(filePath).toFile(), password);
	}
	
	public String getText() throws IOException {
		PDFTextStripper stripper = new PDFTextStripper();
		int lastPage = document.getNumberOfPages();
		stripper.setStartPage(1);
		stripper.setEndPage(lastPage); 
		try {
			return stripper.getText(document);
		} finally {
			if (document != null) {
				document.close();
				document = null;
			}
		}
	}
	
	
}
