package thiagocury.eti.br.minutotrem;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragCartaoSim extends Fragment {

	public FragCartaoSim () {
		// Required empty public constructor
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate (R.layout.fragment_frag_cartao_sim, container, false);
	}

}
