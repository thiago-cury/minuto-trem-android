package thiagocury.eti.br.minutotrem.misc;

import android.content.Context;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;

import thiagocury.eti.br.minutotrem.R;

/**
 * Created by thiagocury on 23/01/2018.
 */

public class CustomClusterRenderer extends DefaultClusterRenderer<Estacao> {

    private final Context mContext;

    public CustomClusterRenderer(Context context, GoogleMap map,
                                 ClusterManager<Estacao> clusterManager) {
        super(context, map, clusterManager);

        mContext = context;
    }

    @Override
    protected void onBeforeClusterItemRendered(Estacao item, MarkerOptions markerOptions) {
        super.onBeforeClusterItemRendered(item, markerOptions);

        if(item.getAtivo()) {
            final BitmapDescriptor markerDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.ic_aeromovel_azul);
            markerOptions.icon(markerDescriptor).snippet(item.getDescricao());
        } else {
            final BitmapDescriptor markerDescriptor = BitmapDescriptorFactory.fromResource(R.mipmap.ic_aeromovel_fora_servico);
            markerOptions.icon(markerDescriptor).snippet(item.getDescricao());
        }
    }
}
