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
public class NotaNegociacaoBovespa extends NotaNegociacao {
	
	private String NrNotaNegociacao;
	private Double debentures;
	private Double vendasAVista;
	private Double comprasAVista;
	private Double opcoesCompras;
	private Double opcoesVendas;
	private Double operacoesATermo;
	private Double valorDasOpTitulosPublicos;
	private Double valorDasOperacaoes;
	private Double totalCBLC;
	private Double valorLiquidoDasOperacoes;
	private Double taxaDeLiquidacao;
	private Double taxaDeRegistro;
	private Double totalBovespa;
	private Double taxaDeTermo;
	private Double taxaANA;
	private Double emolumentos;
	private Double totalCustos;
	private Double taxaOperacional;
	private Double execucao;
	private Double taxaDeCustodia;
	private Double imposto;
	private Double IRRF;
	private Double outros;
	private Double liquido;

	@Override
	public Double getTotal() {
		return liquido;
	}
}
