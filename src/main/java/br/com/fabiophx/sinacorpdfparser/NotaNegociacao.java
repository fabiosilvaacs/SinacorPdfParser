package br.com.fabiophx.sinacorpdfparser;

import java.util.EnumSet;

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
public abstract class NotaNegociacao {
	
	private String DataPregao;
	private Double total;
	
    public enum Tipos {
        BOVESPA,
        BMF;
        public static final EnumSet<Tipos> NAO_IDENTIFICADO = EnumSet.noneOf(Tipos.class);
    }
}
