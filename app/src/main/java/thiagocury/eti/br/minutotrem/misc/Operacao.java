package thiagocury.eti.br.minutotrem.misc;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;

@Generated("org.jsonschema2pojo")
public class Operacao {

	@SerializedName("motivo")
	@Expose
	private String motivo;
	@SerializedName("tipo")
	@Expose
	private Object tipo;
	@SerializedName("intervalos")
	@Expose
	private List<Intervalo> intervalos = new ArrayList<Intervalo> ();
	@SerializedName("trechos")
	@Expose
	private Object trechos;
	@SerializedName("status-situacao-operacional")
	@Expose
	private int statusSituacaoOperacional;
	@SerializedName("descricao-situacao-operacional")
	@Expose
	private String descricaoSituacaoOperacional;
	@SerializedName("tem-previsao-normalizacao")
	@Expose
	private boolean temPrevisaoNormalizacao;
	@SerializedName("horario-previsao-normalizacao")
	@Expose
	private Object horarioPrevisaoNormalizacao;
	@SerializedName("intervalo-entre-trens")
	@Expose
	private Object intervaloEntreTrens;
	@SerializedName("historico-uo")
	@Expose
	private String historicoUo;
	@SerializedName("historico-data")
	@Expose
	private String historicoData;
	@SerializedName("consulta-data")
	@Expose
	private String consultaData;
	@SerializedName("consulta-hora")
	@Expose
	private String consultaHora;

	/**
	 * No args constructor for use in serialization
	 */
	public Operacao () {
	}

	/**
	 * @param intervaloEntreTrens
	 * @param horarioPrevisaoNormalizacao
	 * @param historicoUo
	 * @param historicoData
	 * @param intervalos
	 * @param motivo
	 * @param consultaHora
	 * @param statusSituacaoOperacional
	 * @param consultaData
	 * @param tipo
	 * @param temPrevisaoNormalizacao
	 * @param trechos
	 * @param descricaoSituacaoOperacional
	 */
	public Operacao (String motivo, Object tipo, List<Intervalo> intervalos, Object trechos, int statusSituacaoOperacional, String descricaoSituacaoOperacional, boolean temPrevisaoNormalizacao, Object horarioPrevisaoNormalizacao, Object intervaloEntreTrens, String historicoUo, String historicoData, String consultaData, String consultaHora) {
		this.motivo = motivo;
		this.tipo = tipo;
		this.intervalos = intervalos;
		this.trechos = trechos;
		this.statusSituacaoOperacional = statusSituacaoOperacional;
		this.descricaoSituacaoOperacional = descricaoSituacaoOperacional;
		this.temPrevisaoNormalizacao = temPrevisaoNormalizacao;
		this.horarioPrevisaoNormalizacao = horarioPrevisaoNormalizacao;
		this.intervaloEntreTrens = intervaloEntreTrens;
		this.historicoUo = historicoUo;
		this.historicoData = historicoData;
		this.consultaData = consultaData;
		this.consultaHora = consultaHora;
	}

	/**
	 * @return The motivo
	 */
	public Object getMotivo () {
		return motivo;
	}

	/**
	 * @param motivo The motivo
	 */
	public void setMotivo (String motivo) {
		this.motivo = motivo;
	}

	/**
	 * @return The tipo
	 */
	public Object getTipo () {
		return tipo;
	}

	/**
	 * @param tipo The tipo
	 */
	public void setTipo (Object tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return The intervalos
	 */
	public List<Intervalo> getIntervalos () {
		return intervalos;
	}

	/**
	 * @param intervalos The intervalos
	 */
	public void setIntervalos (List<Intervalo> intervalos) {
		this.intervalos = intervalos;
	}

	/**
	 * @return The trechos
	 */
	public Object getTrechos () {
		return trechos;
	}

	/**
	 * @param trechos The trechos
	 */
	public void setTrechos (Object trechos) {
		this.trechos = trechos;
	}

	/**
	 * @return The statusSituacaoOperacional
	 */
	public int getStatusSituacaoOperacional () {
		return statusSituacaoOperacional;
	}

	/**
	 * @param statusSituacaoOperacional The status-situacao-operacional
	 */
	public void setStatusSituacaoOperacional (int statusSituacaoOperacional) {
		this.statusSituacaoOperacional = statusSituacaoOperacional;
	}

	/**
	 * @return The descricaoSituacaoOperacional
	 */
	public String getDescricaoSituacaoOperacional () {
		return descricaoSituacaoOperacional;
	}

	/**
	 * @param descricaoSituacaoOperacional The descricao-situacao-operacional
	 */
	public void setDescricaoSituacaoOperacional (String descricaoSituacaoOperacional) {
		this.descricaoSituacaoOperacional = descricaoSituacaoOperacional;
	}

	/**
	 * @return The temPrevisaoNormalizacao
	 */
	public boolean getTemPrevisaoNormalizacao () {
		return temPrevisaoNormalizacao;
	}

	/**
	 * @param temPrevisaoNormalizacao The tem-previsao-normalizacao
	 */
	public void setTemPrevisaoNormalizacao (boolean temPrevisaoNormalizacao) {
		this.temPrevisaoNormalizacao = temPrevisaoNormalizacao;
	}

	/**
	 * @return The horarioPrevisaoNormalizacao
	 */
	public Object getHorarioPrevisaoNormalizacao () {
		return horarioPrevisaoNormalizacao;
	}

	/**
	 * @param horarioPrevisaoNormalizacao The horario-previsao-normalizacao
	 */
	public void setHorarioPrevisaoNormalizacao (Object horarioPrevisaoNormalizacao) {
		this.horarioPrevisaoNormalizacao = horarioPrevisaoNormalizacao;
	}

	/**
	 * @return The intervaloEntreTrens
	 */
	public Object getIntervaloEntreTrens () {
		return intervaloEntreTrens;
	}

	/**
	 * @param intervaloEntreTrens The intervalo-entre-trens
	 */
	public void setIntervaloEntreTrens (Object intervaloEntreTrens) {
		this.intervaloEntreTrens = intervaloEntreTrens;
	}

	/**
	 * @return The historicoUo
	 */
	public String getHistoricoUo () {
		return historicoUo;
	}

	/**
	 * @param historicoUo The historico-uo
	 */
	public void setHistoricoUo (String historicoUo) {
		this.historicoUo = historicoUo;
	}

	/**
	 * @return The historicoData
	 */
	public String getHistoricoData () {
		return historicoData;
	}

	/**
	 * @param historicoData The historico-data
	 */
	public void setHistoricoData (String historicoData) {
		this.historicoData = historicoData;
	}

	/**
	 * @return The consultaData
	 */
	public String getConsultaData () {
		return consultaData;
	}

	/**
	 * @param consultaData The consulta-data
	 */
	public void setConsultaData (String consultaData) {
		this.consultaData = consultaData;
	}

	/**
	 * @return The consultaHora
	 */
	public String getConsultaHora () {
		return consultaHora;
	}

	/**
	 * @param consultaHora The consulta-hora
	 */
	public void setConsultaHora (String consultaHora) {
		this.consultaHora = consultaHora;
	}

	@Override
	public String toString () {
		final StringBuffer sb = new StringBuffer ();
		sb.append ("motivo=").append (motivo);
		sb.append ("\ntipo=").append (tipo);
		sb.append ("\nintervalos=").append (intervalos);
		sb.append ("\ntrechos=").append (trechos);
		sb.append ("\nstatusSituacaoOperacional=").append (statusSituacaoOperacional);
		sb.append ("\ndescricaoSituacaoOperacional='").append (descricaoSituacaoOperacional).append ('\n');
		sb.append ("\ntemPrevisaoNormalizacao=").append (temPrevisaoNormalizacao);
		sb.append ("\nhorarioPrevisaoNormalizacao=").append (horarioPrevisaoNormalizacao);
		sb.append ("\nintervaloEntreTrens=").append (intervaloEntreTrens);
		sb.append ("\nhistoricoUo='").append (historicoUo).append ('\n');
		sb.append ("\nhistoricoData='").append (historicoData).append ('\n');
		sb.append ("\nconsultaData='").append (consultaData).append ('\n');
		sb.append ("\nconsultaHora='").append (consultaHora).append ('\n');
		return sb.toString ();
	}
}