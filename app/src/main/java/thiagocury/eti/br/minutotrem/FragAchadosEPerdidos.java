package thiagocury.eti.br.minutotrem;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragAchadosEPerdidos extends android.app.Fragment {

	public FragAchadosEPerdidos () {
		// Required empty public constructor
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate (R.layout.fragment_frag_achados_e_perdidos, container, false);
	}

}
