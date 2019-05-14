package thiagocury.eti.br.minutotrem;

import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterManager;

import java.util.ArrayList;

import thiagocury.eti.br.minutotrem.misc.CustomClusterRenderer;
import thiagocury.eti.br.minutotrem.misc.Estacao;

public class TelaMapaEstacoes extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    private ArrayList<Estacao> ests = null;

    private ClusterManager<Estacao> cluster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_mapa_estacoes);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        ests = new ArrayList<>();
        ests = TelaInicial.ests;

        ActivityCompat.requestPermissions(TelaMapaEstacoes.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 0);

        if (ContextCompat.checkSelfPermission(TelaMapaEstacoes.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(TelaMapaEstacoes.this, android.Manifest.permission.ACCESS_FINE_LOCATION)) {
                /*Toast.makeText(
                        getBaseContext(),
                        getResources().getString(R.string.explicacao1) +
                                "\n" + getResources().getString(R.string.explicacao2),
                        Toast.LENGTH_LONG).show();*/
                ActivityCompat.requestPermissions(TelaMapaEstacoes.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            } else {
                ActivityCompat.requestPermissions(TelaMapaEstacoes.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 0);
            }
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setZoomControlsEnabled(true);

        cluster = new ClusterManager<Estacao>(this, mMap);

        mMap.setOnCameraIdleListener(cluster);
        mMap.setOnMarkerClickListener(cluster);

        if (ContextCompat.checkSelfPermission(TelaMapaEstacoes.this, android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            mMap.setMyLocationEnabled(true);
        }

        if(!ests.isEmpty()) {
            cluster.addItems(ests);
        }

        cluster.setOnClusterItemClickListener(new ClusterManager.OnClusterItemClickListener<Estacao>() {
            @Override
            public boolean onClusterItemClick(Estacao est) {

                Toast.makeText(getBaseContext(), est.getDescricao(), Toast.LENGTH_SHORT).show();

                return true;
            }
        });

        final CustomClusterRenderer renderer = new CustomClusterRenderer(this, mMap, cluster);
        cluster.setRenderer(renderer);

       /* mMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker) {

                Estacao e = null;

                for (int i = 0; i < ests.size(); i++) {
                    if (marker.getTitle().equalsIgnoreCase(ests.get(i).getDescricao())){
                        e = ests.get(i);
                    }
                }

                Intent it = new Intent(TelaMapa.this, TemperaturaDetalhe.class);
                it.putExtra("t",t);
                startActivity(it);

                return true;
            }
        });*/

        //Movendo a camera para a estação Luiz Pasteur no início.
        LatLng latLng = new LatLng(-29.83261128, -51.16509887);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        //Movimentando o zoom da camera no map
        mMap.moveCamera(CameraUpdateFactory.zoomTo(10));
    }
}
