package br.com.fabiophx.sinacorpdfparser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

public class PdfReader {
	
	public static String getText(Path path, String password) throws IOException {
		return getText(path.toFile(), password);
	}

	public static String getText(File pdf, String password) throws IOException {
		PDDocument document = PDDocument.load(pdf, password);

		try (document) {
			// PDFTextStripperByArea stripperArea = new PDFTextStripperByArea();
			// stripperArea.setSortByPosition(true);
			PDFTextStripper stripper = new PDFTextStripper();
			int lastPage = document.getNumberOfPages();
			stripper.setStartPage(1);
			stripper.setEndPage(lastPage);
			return stripper.getText(document);
		}
	}
	
}
