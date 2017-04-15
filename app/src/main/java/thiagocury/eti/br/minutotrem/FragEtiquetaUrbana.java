package thiagocury.eti.br.minutotrem;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import thiagocury.eti.br.minutotrem.misc.Etiqueta;
import thiagocury.eti.br.minutotrem.misc.EtiquetaAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragEtiquetaUrbana extends Fragment {

	private GridView grid;
	private EtiquetaAdapter etiquetaAdapter;

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View v = inflater.inflate (R.layout.fragment_frag_etiqueta_urbana, container, false);

		grid = (GridView) v.findViewById (R.id.grid_etiqueta);

		return v;
	}

	@Override
	public void onStart () {
		super.onStart ();

		//Quando sai e entra denovo no app
		//Previne crashes!
		if (getActivity () == null) return;

		etiquetaAdapter = new EtiquetaAdapter (getActivity ().getBaseContext (), Etiqueta.getEtiquetas ());
		grid.setAdapter (etiquetaAdapter);
		grid.deferNotifyDataSetChanged ();
	}
}
