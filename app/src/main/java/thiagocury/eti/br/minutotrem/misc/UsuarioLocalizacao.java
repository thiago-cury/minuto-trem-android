package thiagocury.eti.br.minutotrem.misc;

import com.google.android.gms.maps.model.LatLng;

/***
 * @author Leandro Soares Quevedo
 * @version 1.0
 * @since 16/09/16
 ***/
public class UsuarioLocalizacao {

	private double latitude;
	private double longitude;
	private double speed;

	public UsuarioLocalizacao () {
	}

	public UsuarioLocalizacao (double latitude, double longitude, double speed) {
		this.latitude = latitude;
		this.longitude = longitude;
		this.speed = speed;
	}

	public LatLng getPosicaoAtual () {
		return new LatLng (latitude, longitude);
	}

	public double getLatitude () {
		return latitude;
	}

	public void setLatitude (double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude () {
		return longitude;
	}

	public void setLongitude (double longitude) {
		this.longitude = longitude;
	}

	public double getSpeed () {
		return speed;
	}

	public void setSpeed (double speed) {
		this.speed = speed;
	}

	@Override
	public String toString () {
		return "UsuarioLocalizacao{" +
				"latitude=" + latitude +
				", longitude=" + longitude +
				", speed=" + speed +
				'}';
	}

}
