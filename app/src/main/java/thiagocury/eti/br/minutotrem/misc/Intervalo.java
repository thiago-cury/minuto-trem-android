package thiagocury.eti.br.minutotrem.misc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Intervalo {

	@SerializedName("intervalo")
	@Expose
	private int intervalo;
	@SerializedName("estacao-partida")
	@Expose
	private String estacaoPartida;
	@SerializedName("estacao-chegada")
	@Expose
	private String estacaoChegada;

	/**
	 * No args constructor for use in serialization
	 */
	public Intervalo () {
	}

	/**
	 * @param estacaoChegada
	 * @param estacaoPartida
	 * @param intervalo
	 */
	public Intervalo (int intervalo, String estacaoPartida, String estacaoChegada) {
		this.intervalo = intervalo;
		this.estacaoPartida = estacaoPartida;
		this.estacaoChegada = estacaoChegada;
	}

	/**
	 * @return The intervalo
	 */
	public int getIntervalo () {
		return intervalo;
	}

	/**
	 * @param intervalo The intervalo
	 */
	public void setIntervalo (int intervalo) {
		this.intervalo = intervalo;
	}

	/**
	 * @return The estacaoPartida
	 */
	public String getEstacaoPartida () {
		return estacaoPartida;
	}

	/**
	 * @param estacaoPartida The estacao-partida
	 */
	public void setEstacaoPartida (String estacaoPartida) {
		this.estacaoPartida = estacaoPartida;
	}

	/**
	 * @return The estacaoChegada
	 */
	public String getEstacaoChegada () {
		return estacaoChegada;
	}

	/**
	 * @param estacaoChegada The estacao-chegada
	 */
	public void setEstacaoChegada (String estacaoChegada) {
		this.estacaoChegada = estacaoChegada;
	}

	@Override
	public String toString () {
		final StringBuffer sb = new StringBuffer ();
		sb.append ("intervalo=").append (intervalo);
		sb.append ("\nestacaoPartida='").append (estacaoPartida).append ('\n');
		sb.append ("\nestacaoChegada='").append (estacaoChegada).append ('\n');
		return sb.toString ();
	}
}