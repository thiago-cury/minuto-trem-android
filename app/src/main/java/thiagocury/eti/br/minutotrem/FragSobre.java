package thiagocury.eti.br.minutotrem;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragSobre extends Fragment {

	public FragSobre () {
		// Required empty public constructor
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		//Toast.makeText (getActivity ().getBaseContext (), "Olá, seja bem vindo!", Toast.LENGTH_SHORT).show ();
		return inflater.inflate (R.layout.fragment_frag_sobre, container, false);
	}
}
