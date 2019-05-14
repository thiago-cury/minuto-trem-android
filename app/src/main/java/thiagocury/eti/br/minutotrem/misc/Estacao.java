package thiagocury.eti.br.minutotrem.misc;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

import java.io.Serializable;

/***
 * @author Leandro Soares Quevedo
 * @version 1.0
 * @since 16/09/16
 ***/
public class Estacao implements Serializable, ClusterItem {

	private String key;
	private String imagem;//
	private String altitude;//
	private String descricao;//
	private String endereco;//
	private String cidade;//
	private float latitude;//
	private float longitude;//
	private boolean ativo;//

	public Estacao() {
	}

	public Estacao(String key, String imagem, String altitude, String descricao, String endereco, String cidade, float latitude, float longitude, boolean ativo) {
		this.key = key;
		this.imagem = imagem;
		this.altitude = altitude;
		this.descricao = descricao;
		this.endereco = endereco;
		this.cidade = cidade;
		this.latitude = latitude;
		this.longitude = longitude;
		this.ativo = ativo;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(String imagem) {
		this.imagem = imagem;
	}

	public String getAltitude() {
		return altitude;
	}

	public void setAltitude(String altitude) {
		this.altitude = altitude;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public boolean getAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Estacao{" +
				"key='" + key + '\'' +
				", imagem='" + imagem + '\'' +
				", altitude='" + altitude + '\'' +
				", descricao='" + descricao + '\'' +
				", endereco='" + endereco + '\'' +
				", cidade='" + cidade + '\'' +
				", latitude=" + latitude +
				", longitude=" + longitude +
				", ativo=" + ativo +
				'}';
	}

	@Override
	public LatLng getPosition() {
		LatLng pos = new LatLng(getLatitude(),getLongitude());
		return pos;
	}
}
