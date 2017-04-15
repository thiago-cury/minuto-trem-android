package thiagocury.eti.br.minutotrem.misc;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/***
 * @author Leandro Soares Quevedo
 * @version 1.0
 * @since 16/09/16
 ***/
public class Estacao implements Serializable {

	private String imagem;
	private String altitude;
	private String descricao;
	private String endereco;
	private String cidade;
	private float latitude;
	private float longitude;
	private boolean ativo;

	public Estacao () {
	}

	public Estacao (String altitude, String descricao, String endereco, String cidade, float latitude, float longitude) {
		this.altitude = altitude;
		this.descricao = descricao;
		this.endereco = endereco;
		this.cidade = cidade;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Estacao (String altitude, String descricao, float latitude, float longitude) {
		this.altitude = altitude;
		this.descricao = descricao;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public Estacao (String imagem, String altitude, String descricao, String endereco, String cidade, float latitude, float longitude, boolean ativo) {
		this.imagem = imagem;
		this.altitude = altitude;
		this.descricao = descricao;
		this.endereco = endereco;
		this.cidade = cidade;
		this.latitude = latitude;
		this.longitude = longitude;
		this.ativo = ativo;
	}

	public Estacao (String imagem, String altitude, String descricao, String endereco, String cidade, float latitude, float longitude) {
		this.imagem = imagem;
		this.altitude = altitude;
		this.descricao = descricao;
		this.endereco = endereco;
		this.cidade = cidade;
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public LatLng getPosicao () {
		return new LatLng (latitude, longitude);
	}

	public String getImagem () {
		return imagem;
	}

	public void setImagem (String imagem) {
		this.imagem = imagem;
	}

	public String getAltitude () {
		return altitude;
	}

	public void setAltitude (String altitude) {
		this.altitude = altitude;
	}

	public String getDescricao () {
		return descricao;
	}

	public void setDescricao (String descricao) {
		this.descricao = descricao;
	}

	public String getEndereco () {
		return endereco;
	}

	public void setEndereco (String endereco) {
		this.endereco = endereco;
	}

	public String getCidade () {
		return cidade;
	}

	public void setCidade (String cidade) {
		this.cidade = cidade;
	}

	public float getLatitude () {
		return latitude;
	}

	public void setLatitude (float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude () {
		return longitude;
	}

	public void setLongitude (float longitude) {
		this.longitude = longitude;
	}

	public boolean getAtivo () {
		return ativo;
	}

	public void setAtivo (boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString () {
		return "Estacao{" +
				"altitude='" + altitude + '\'' +
				", descricao='" + descricao + '\'' +
				", endereco='" + endereco + '\'' +
				", cidade='" + cidade + '\'' +
				", latitude=" + latitude +
				", longitude=" + longitude +
				'}';
	}

}
