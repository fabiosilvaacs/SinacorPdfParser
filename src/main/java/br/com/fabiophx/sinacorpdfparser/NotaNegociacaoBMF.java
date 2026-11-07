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
public final class NotaNegociacaoBMF extends NotaNegociacao {

	private BigDecimal vendaDisponivel;

	private BigDecimal compraDisponivel;

	private BigDecimal IRRFDayTrade;

	private BigDecimal taxasBMF;

	private BigDecimal outrosCustos;

	private BigDecimal ajustePosicao;

	private BigDecimal ajusteDayTrade;

	private BigDecimal totalCustosOperacionais;

	private BigDecimal IRRFOperacional;

	private BigDecimal totalContaInvestimento;

	private BigDecimal totalContaNormal;

	private BigDecimal totalLiquidoNota;
}
