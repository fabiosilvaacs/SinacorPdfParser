package br.com.fabiophx.sinacorpdfparser;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@EqualsAndHashCode(callSuper = true)
@ToString()
@NoArgsConstructor
@AllArgsConstructor
public final class NotaNegociacaoBovespa extends NotaNegociacao {

	private BigDecimal debentures;

	private BigDecimal vendasAVista;

	private BigDecimal comprasAVista;

	private BigDecimal operacoesATermo;

	private BigDecimal valorOperacoesTitulosPublicos;

	private BigDecimal valorOperacoes;

	private BigDecimal taxaLiquidacao;

	private BigDecimal totalCBLC;

	private BigDecimal taxaTermoOpcoes;

	private BigDecimal taxaANA;

	private BigDecimal emolumentos;

	private BigDecimal totalBovespa;

	private BigDecimal execucao;

	private BigDecimal taxaCustodia;

	private BigDecimal totalCustos;
}
