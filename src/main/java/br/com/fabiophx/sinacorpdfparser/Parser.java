package br.com.fabiophx.sinacorpdfparser;

import java.util.List;

/**
 * Interface que deve ser implementada pelo parser.
 */

public interface Parser {
	
	List<NotaNegociacao> getNotas();

	Parser find(String text);
}
