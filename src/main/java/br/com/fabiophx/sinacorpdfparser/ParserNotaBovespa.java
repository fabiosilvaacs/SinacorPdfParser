package br.com.fabiophx.sinacorpdfparser;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ParserNotaBovespa implements ParserNota {
	
	private ArrayList<NotaNegociacao> notas;

	public ParserNotaBovespa() {
		notas = new ArrayList<NotaNegociacao>();
	}

	@Override
	public ParserNota find(String text) {
		String [] lines = text.split("\r\n|\r|\n");
		
		notas = new ArrayList<NotaNegociacao>();
		
		NotaNegociacaoBovespa notaBovespa = new NotaNegociacaoBovespa();
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].contains("Resumo dos Negócios")) {
				notaBovespa.setDataPregao(lines[i - 40]);
				notaBovespa.setNrNota(lines[i - 44]);
				notaBovespa.setDebentures(extract(lines[i - 8]));
				notaBovespa.setVendasAVista(extract(lines[i - 7]));
				notaBovespa.setComprasAVista(extract(lines[i - 6]));
				notaBovespa.setComprasOpcoes(extract(lines[i - 5]));
				notaBovespa.setVendasOpcoes(extract(lines[i - 4]));
				notaBovespa.setOperacoesATermo(extract(lines[i - 3]));
				notaBovespa.setValorOperacoesTitulosPublicos(extract(lines[i - 2]));
				notaBovespa.setValorOperacoes(extract(lines[i - 1]));
			}
			
			if (lines[i].contains("Resumo Financeiro")) {
				notaBovespa.setTotalCBLC(extract(tokenizeBov(lines[i - 18])));
				notaBovespa.setValorNegocios(extract(tokenizeBov(lines[i - 17])));
				notaBovespa.setTaxaLiquidacao(extract(tokenizeBov(lines[i - 16])));
				notaBovespa.setTaxaRegistro(extract(tokenizeBov(lines[i - 15])));
				notaBovespa.setTotalBovespa(extract(tokenizeBov(lines[i - 14])));
				notaBovespa.setTaxaTermoOpcoes(extract(tokenizeBov(lines[i - 13])));
				notaBovespa.setTaxaANA(extract(tokenizeBov(lines[i - 12])));
				notaBovespa.setEmolumentos(extract(tokenizeBov(lines[i - 11])));
				notaBovespa.setTotalCustos(extract(tokenizeBov(lines[i - 9])));
				notaBovespa.setTaxaOperacional(extract(tokenizeBov(lines[i - 7])));
				notaBovespa.setExecucao(extract(tokenizeBov(lines[i - 6])));
				notaBovespa.setTaxaCustodia(extract(tokenizeBov(lines[i - 5])));
				notaBovespa.setImpostos(extract(tokenizeBov(lines[i - 4])));
				notaBovespa.setIRRF(extract(tokenizeBov(lines[i - 3])));
				notaBovespa.setOutros(extract(tokenizeBov(lines[i - 2])));
				notaBovespa.setTotalLiquido(extract(tokenizeBov(lines[i - 1])));

				notas.add(notaBovespa);
				notaBovespa = new NotaNegociacaoBovespa();
			}
		}
		return this;
	}
	
	private String tokenizeBov(String line) {
		Pattern pattern = Pattern.compile("\\d+([\\.\\,]\\d+)+");
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        return matcher.group();
	}
	
	private BigDecimal extract(String text) {
		String [] textSplitted = text.replace(".", "").replace(",", ".").split("\\|") ;
		BigDecimal amount = new BigDecimal(textSplitted[0].trim());
		if(textSplitted.length >= 2) {
			amount = (textSplitted[1].trim()).equalsIgnoreCase("D") ? amount.negate() : amount;
		}
		return amount;
	}

	@Override
	public List<NotaNegociacao> getNotas() {
		return notas;
	}
}
