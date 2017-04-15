package thiagocury.eti.br.minutotrem;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import thiagocury.eti.br.minutotrem.misc.Ciclista;
import thiagocury.eti.br.minutotrem.misc.CiclistaAdapter;

public class FragGuiaCiclista extends Fragment {

	private CiclistaAdapter adapterCiclista;
	private ListView lvGuiaCiclista;
	private ArrayList<Ciclista> ciclistas;

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate (R.layout.fragment_frag_guia_ciclista, container, false);

		lvGuiaCiclista = (ListView) v.findViewById (R.id.lv_guia_ciclista);

		return v;
	}

	@Override
	public void onStart () {
		super.onStart ();

		//Buscando Estações do Parse
		final ParseQuery<ParseObject> query = ParseQuery.getQuery ("ciclista");

		//Buscando ordenando por código do produto
		query.orderByAscending ("categoria");

		query.findInBackground (new FindCallback<ParseObject> () {
			@Override
			public void done (List<ParseObject> list, ParseException error) {
				if (error == null) {
					ciclistas = new ArrayList<> ();
					for (int i = 0; i < list.size (); i++) {
						Ciclista ciclista = new Ciclista ();
						ciclista.setDescricao (list.get (i).getString ("descricao"));
						ciclista.setCategoria (list.get (i).getString ("categoria"));
						ciclista.setImagem (list.get (i).getString ("imagem"));

						ciclistas.add (ciclista);
					}

					//Quando entrar e sair
					if (getActivity () == null) return;

					adapterCiclista = new CiclistaAdapter (getActivity ().getBaseContext (), ciclistas);
					lvGuiaCiclista.setAdapter (adapterCiclista);
					adapterCiclista.notifyDataSetChanged ();
				} else {
					Crouton.makeText (
							getActivity (),
							getResources ().getString (R.string.aviso_geral_erro) + error.getMessage (),
							Style.ALERT
					).show ();
					error.printStackTrace ();
				}
			}
		});
	}
}
