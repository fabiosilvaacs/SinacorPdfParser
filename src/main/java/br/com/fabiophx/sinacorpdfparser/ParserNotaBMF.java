package br.com.fabiophx.sinacorpdfparser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ParserNotaBMF implements ParserNota {
	
	private ArrayList<NotaNegociacao> notas;

	public ParserNotaBMF() {
		notas = new ArrayList<>();
	}

	@Override
	public ParserNota find(String text) {
		String[] lines = text.split("\r\n|\r|\n");
		
		notas = new ArrayList<>();
		
		NotaNegociacaoBMF nota = new NotaNegociacaoBMF();
		for (int i = 0; i < lines.length; i++) {

			if (lines[i].contains("NOTA DE NEGOCIAÇÃO Nr. nota")) {
				nota.setNrNota(lines[i + 1].trim());
			}

			if (lines[i].contains("Data pregão")) {
				nota.setDataPregao(lines[i + 1].trim());
			}

			if (lines[i].contains("Valor dos negócios")) {
				if (!lines[(i + 1)].trim().isEmpty()) {
					nota.setVendaDisponivel(tokenize(lines[(i + 1)], 0));
					nota.setCompraDisponivel(tokenize(lines[(i + 1)], 1));
					nota.setVendasOpcoes(tokenize(lines[(i + 1)], 2));
					nota.setComprasOpcoes(tokenize(lines[(i + 1)], 3));
					nota.setValorNegocios(tokenize(lines[(i + 1)], 4));
				}
			}

			if (lines[i].contains("BM&F Taxas BM&F (emol+f.gar)")) {
				if (!lines[(i + 1)].trim().isEmpty()) {
					nota.setIRRF(tokenize(lines[(i + 1)], 0));
					nota.setIRRFDayTrade(tokenize(lines[(i + 1)], 1));
					nota.setTaxaOperacional(tokenize(lines[(i + 1)], 2));
					nota.setTaxaRegistro(tokenize(lines[(i + 1)], 3));
					nota.setTaxasBMF(tokenize(lines[(i + 1)], 4));
				}
			}

			if (lines[i].contains("Total de custos operacionais")) {
				if (!lines[(i + 1)].trim().isEmpty()) {
					nota.setOutrosCustos(tokenize(lines[(i + 1)], 0));
					nota.setImpostos(tokenize(lines[(i + 1)], 1));
					nota.setAjustePosicao(tokenize(lines[(i + 1)], 2));
					nota.setAjusteDayTrade(tokenize(lines[(i + 1)], 3));
					nota.setTotalCustosOperacionais(tokenize(lines[(i + 1)], 4));
				}
			}

			if (lines[i].contains("Conta Normal Total liquido (#) Total líquido da not")) {
				if (!lines[(i + 1)].trim().isEmpty()) {
					nota.setOutros(tokenize(lines[(i + 1)], 0));
					nota.setIRRFOperacional(tokenize(lines[(i + 1)], 1));
					nota.setTotalContaInvestimento(tokenize(lines[(i + 1)], 2));
					nota.setTotalContaNormal(tokenize(lines[(i + 1)], 3));
					nota.setTotalLiquido(tokenize(lines[(i + 1)], 4));
					nota.setTotalLiquidoNota(tokenize(lines[(i + 1)], 5));

					notas.add(nota);
					nota = new NotaNegociacaoBMF();
				}
			}
		}
		return this;
	}

	public BigDecimal tokenize(String line, int tokenIndex) {
		ArrayList<String> tokens = new ArrayList<>();
		int begin = 0;
		line = line.trim();
		char priorChar = line.charAt(0);
		for (int i = 0; i < line.length(); i++) {
			char currentChar = line.charAt(i);
			if ((Character.isDigit(currentChar)) && priorChar == ' ') {
				tokens.add((line.substring(begin, i)).trim());
				begin = i;
			}
			if (i + 1 == line.length()) {
				tokens.add((line.substring(begin, i + 1)).trim());
			}
			priorChar = currentChar;
		}
		return extract(tokens.get(tokenIndex));
	}

	public BigDecimal extract(String text) {
		String[] textSplitted = text.replace(".", "").replace(",", ".").split("\\|");
		BigDecimal amount = new BigDecimal(textSplitted[0].trim());
		if (textSplitted.length >= 2) {
			amount = (textSplitted[1].trim()).equalsIgnoreCase("D") ? amount.negate() : amount;
		}
		return amount;
	}

	@Override
	public List<NotaNegociacao> getNotas() {
		return notas;
	}

}
