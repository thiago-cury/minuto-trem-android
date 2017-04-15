package thiagocury.eti.br.minutotrem;

import android.content.Context;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import thiagocury.eti.br.minutotrem.misc.Estacao;
import thiagocury.eti.br.minutotrem.misc.UsuarioLocalizacao;
import thiagocury.eti.br.minutotrem.misc.Utils;

public class FragModoTrem extends FragmentActivity implements OnMapReadyCallback, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

	private GoogleMap mapa;
	private ArrayList<Estacao> estacoes;

	//Atributos para o player
	private MediaPlayer mediaPlayer;

	//Atributos usados pra traçar a rota
	private List<LatLng> list;
	private Polyline polyline;
	private String json;

	//Atributos usados para achar ultima localização
	private GoogleApiClient googleApiClient;
	private LocationManager locationManager;
	private LocationListener locationListener;
	private Location localizacaoAtual;
	private UsuarioLocalizacao usuarioLocalizacao = null;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate (savedInstanceState);
		setContentView (R.layout.fragment_frag_modo_trem);
		//inicializarMapaSeNecessario ();

		//Configura o objeto GoogleApiClient
		//googleApiClient = new GoogleApiClient.Builder (this).addConnectionCallbacks (this).addOnConnectionFailedListener (this).addApi (LocationServices.API).build ();

		//Habilitando gestos
		/*mapa.getUiSettings ().setRotateGesturesEnabled (true);
		mapa.getUiSettings ().setZoomControlsEnabled (true);
		mapa.getUiSettings ().setZoomGesturesEnabled (true);
		mapa.getUiSettings ().setCompassEnabled (true);

		locationManager = (LocationManager) getSystemService (Context.LOCATION_SERVICE);*/

		//Buscando a localização atual do usuário
		/*locationListener = new LocationListener () {
			@Override
			public void onLocationChanged (Location location) {
				usuarioLocalizacao = new UsuarioLocalizacao (location.getLatitude (), location.getLongitude (), location.getSpeed ());

				Estacao estacao = pegarEstacaoMaisProxima ();
				if (e.getDescricao ().equalsIgnoreCase ("AeroMóvel/Terminal 1") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));

				} else if (e.getDescricao ().equalsIgnoreCase ("AeroMóvel/Terminal 2") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));


				} else if (e.getDescricao ().equalsIgnoreCase ("Novo Hamburgo") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Fenac") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Industrial Tintas/Killing") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Santo Afonso") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Rio dos Sinos") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Canoas/LaSalle") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Mercado") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Rodoviária") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("São Leopoldo") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Unisinos") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Sapucaia") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Luiz Pasteur") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Esteio") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Petrobrás") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("São Luis/Ulbra") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Mathias Velho") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Fátima") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Aeroporto") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("Farrapos/IPA") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else if (e.getDescricao ().equalsIgnoreCase ("São Pedro") && verificarDistanciaMaisProxima () < 200) {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_chegada) + " : " + e.getDescricao (), Style.INFO).show ();
					mMap.addMarker (new MarkerOptions ().position (new LatLng (e.getLatitude (), e.getLongitude ())).
							title (getResources ().getString (R.string.act_maps_activity_estacao) + " " + e.getDescricao () + ", " + e.getCidade () + " - RS").
							icon (BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_amarelo)));
				} else {
					Crouton.makeText (MapsActivityTrainMode.this, getResources ().getString (R.string.aviso_longe_estacao), Style.ALERT).show ();

					play (e.getDescricao ());

                //    AlertDialog.Builder dialog = new AlertDialog.Builder(MapsActivityTrainMode.this);
				//	dialog.setTitle("Aviso");
                //   dialog.setMessage("Você deseja verificar qual é a estação mais próxima?");
                //    dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                //        @Override
                //        public void onClick(DialogInterface dialog, int which) {
                //            Intent it = new Intent(MapsActivityTrainMode.this, MapsActivity.class);
                //            startActivity(it);
                //       }
                //    });
                //    dialog.setNegativeButton("Não", null);
                //    dialog.show();
				}
			}

			@Override
			public void onStatusChanged (String s, int i, Bundle bundle) {

			}

			@Override
			public void onProviderEnabled (String s) {

			}

			@Override
			public void onProviderDisabled (String s) {

			}
		}*/
	}//fecha onCreate

	private Estacao pegarEstacaoMaisProxima () {
		//Cria um novo array de estacoe em ordem crescente pela mais próxima
		ArrayList<Estacao> estacoesProximas = new ArrayList<> ();
		for (Estacao e : estacoes) estacoesProximas.add (e);

		Collections.sort (estacoesProximas, new Comparator<Estacao> () {
			@Override
			public int compare (Estacao a, Estacao b) {
				Double x1 = Utils.calcularDistancia (usuarioLocalizacao.getPosicaoAtual (), a.getPosicao ());
				Double x2 = Utils.calcularDistancia (usuarioLocalizacao.getPosicaoAtual (), b.getPosicao ());
				return x1.compareTo (x2);
			}
		});

		return estacoesProximas.get (0);

	}

	private boolean verificarAeromovel (Estacao estacao) {
		return estacao.getDescricao ().equalsIgnoreCase ("AeroMóvel/Terminal 1") || estacao.getDescricao ().equalsIgnoreCase ("AeroMóvel/Terminal 2");
	}

	///Inicializa o mapa, caso necessário
	private void inicializarMapaSeNecessario () {
		if (mapa == null) {

			mapa = ((SupportMapFragment) getSupportFragmentManager ().findFragmentById (R.id.mapa)).getMap ();

			if (mapa != null) inicializarMapa ();
		}
	}

	private void inicializarMapa () {
		mapa.setMyLocationEnabled (true);
		mapa.setMapType (GoogleMap.MAP_TYPE_NORMAL);

		//Movendo a camera para a estação Luiz Pasteur no início.
		LatLng latLng = new LatLng (-29.83261128, -51.16509887);
		mapa.moveCamera (CameraUpdateFactory.newLatLng (latLng));

		//Movimentando o Zoom da Camera do mapa
		mapa.animateCamera (CameraUpdateFactory.zoomTo (10));

		final ParseQuery<ParseObject> query = ParseQuery.getQuery ("localizacao");

		//Buscando ordenando por código do produto
		query.orderByAscending ("latitude");

		query.findInBackground (new FindCallback<ParseObject> () {
			@Override
			public void done (List<ParseObject> list, ParseException error) {
				if (error == null) {
					estacoes = new ArrayList<Estacao> ();
					for (int i = 0; i < list.size (); i++) {
						Estacao estacao = new Estacao ();
						estacao.setAltitude (list.get (i).getString ("altitude"));
						estacao.setDescricao (list.get (i).getString ("descricao"));
						estacao.setEndereco (list.get (i).getString ("endereco"));
						estacao.setCidade (list.get (i).getString ("cidade"));
						estacao.setLatitude ((float) list.get (i).getDouble ("latitude_new"));
						estacao.setLongitude ((float) list.get (i).getDouble ("longitude_new"));
						estacao.setAtivo (list.get (i).getBoolean ("ativo"));

						String estacaoString = getResources ().getString (R.string.frag_modo_trem_estacao);
						String titulo = new StringBuilder ().append (estacaoString).append (" ").append (estacao.getDescricao ()).append (", ").append (estacao.getCidade ()).append (" - RS").toString ();
						BitmapDescriptor icone = null;

						//Caso seja aeromóvel terminal 1 ou 2
						if (verificarAeromovel (estacao)) {
							if (estacao.getAtivo ()) {
								icone = BitmapDescriptorFactory.fromResource (R.mipmap.ic_aeromovel_azul);
							} else {
								icone = BitmapDescriptorFactory.fromResource (R.mipmap.ic_aeromovel_fora_servico);
							}
						} else if (estacao.getAtivo ()) {
							icone = BitmapDescriptorFactory.fromResource (R.mipmap.ic_launcher);
						} else {
							icone = BitmapDescriptorFactory.fromResource (R.mipmap.ic_trem_fora_servico);
						}
						//Adiciona o marcador no mapa
						//mapa.addMarker (new MarkerOptions ().position (new LatLng (estacao.getLatitude (), estacao.getLongitude ())).title (titulo).icon (icone);
						estacoes.add (estacao);
					}

					desenharMalhaFerroviaria ();
				} else {
					Crouton.makeText (FragModoTrem.this, getResources ().getString (R.string.aviso_geral_erro) + error.getMessage (), Style.ALERT).show ();
				}
			}
		});
	}

	private void desenharMalhaFerroviaria () {
		List<LatLng> rotas = new ArrayList<> ();

		for (int i = 0; i < estacoes.size (); i++) {
			rotas.add (new LatLng (estacoes.get (i).getLatitude (), estacoes.get (i).getLongitude ()));
		}

		PolylineOptions linha = new PolylineOptions ().addAll (rotas).width (5).color (Color.BLUE).visible (true);
		Log.d ("FragModoTrem", "Desenhando malha da ferroviária: " + rotas.toString ());
		mapa.addPolyline (linha);
	}

	@Override
	public void onConnected (Bundle bundle) {

	}

	@Override
	public void onConnectionSuspended (int i) {

	}

	@Override
	public void onConnectionFailed (ConnectionResult connectionResult) {

	}

	@Override
	public void onMapReady (GoogleMap googleMap) {

	}
}
