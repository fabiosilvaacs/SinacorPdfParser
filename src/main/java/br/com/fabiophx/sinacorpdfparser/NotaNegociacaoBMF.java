package br.com.fabiophx.sinacorpdfparser;

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
public class NotaNegociacaoBMF extends NotaNegociacao {

	private String NrNotaNegociacao;
	private Double VendaDisponivel;
	private Double CompraDisponivel;
	private Double VendaOpcoes;
	private Double CompraOpcoes;
	private Double ValorDosNegocios;
	private Double IRRF;
	private Double IRRFDayTrade;
	private Double TaxaOperacional;
	private Double TaxaRegistroBMF;
	private Double TaxaBMF;
	private Double OutrosCustos;
	private Double Impostos;
	private Double AjusteDePosicao;
	private Double AjusteDayTrade;
	private Double TotalDeCustosOperacionais;
	private Double Outros;
	private Double IRRFOpercaional;
	private Double TotalContaInvestimento;
	private Double TotalContaNormal;
	private Double TotalLiquido;
	private Double TotalLiquidoDaNota;

	@Override
	public Double getTotal() {
		return TotalLiquidoDaNota;
	}

}
