package br.com.fabiophx.sinacorpdfparser;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode()
@ToString()
@NoArgsConstructor
@AllArgsConstructor
public abstract sealed class NotaNegociacao permits NotaNegociacaoBovespa, NotaNegociacaoBMF {

	private String nrNota;

	private String dataPregao;

	/** Bovespa: Opções - vendas, BMF: Venda Opções */
	private BigDecimal vendasOpcoes;

	/** Bovespa: Opções - compras, BMF: Compra Opções */
	private BigDecimal comprasOpcoes;

	/** Bovespa: Valor líquido das operações, BMF: Valor dos negócios */
	private BigDecimal valorNegocios;

	private BigDecimal IRRF;

	private BigDecimal taxaOperacional;

	/** Bovespa: Taxa de Registro, BMF: Taxa registro BM&F */
	private BigDecimal taxaRegistro;

	private BigDecimal impostos;

	private BigDecimal outros;

	private BigDecimal totalLiquido;
}
