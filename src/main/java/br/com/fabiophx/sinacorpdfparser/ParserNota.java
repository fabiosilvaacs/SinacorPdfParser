package br.com.fabiophx.sinacorpdfparser;

import java.util.List;

public interface ParserNota {
	
	List<NotaNegociacao> getNotas();

	ParserNota find(String text);
}
