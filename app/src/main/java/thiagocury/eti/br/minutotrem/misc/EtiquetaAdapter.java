package thiagocury.eti.br.minutotrem.misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.util.ArrayList;

import thiagocury.eti.br.minutotrem.R;

/***
 * @author Leandro Soares Quevedo
 * @version 1.0
 * @since 11/09/16
 ***/
public class EtiquetaAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Etiqueta> etiquetas;
	private LayoutInflater inflater;

	public EtiquetaAdapter (Context context, ArrayList<Etiqueta> etiquetas) {
		this.context = context;
		this.etiquetas = etiquetas;
		this.inflater = LayoutInflater.from (context);
	}

	@Override
	public int getCount () {
		return etiquetas.size ();
	}

	@Override
	public Object getItem (int i) {
		return etiquetas.get (i);
	}

	@Override
	public long getItemId (int i) {
		return i;
	}

	@Override
	public View getView (int i, View view, ViewGroup viewGroup) {
		ViewHolder holder;

		if (view == null) {
			view = inflater.inflate (R.layout.linha_etiqueta, null);
			view.setLayoutParams (new GridView.LayoutParams (GridView.AUTO_FIT, 300));
			holder = new ViewHolder (view);
			view.setTag (holder);
		} else {
			holder = (ViewHolder) view.getTag ();
		}

		Etiqueta etiqueta = (Etiqueta) getItem (i);
		holder.ivImagem.setImageResource (etiqueta.getImagem ());

		return view;
	}

	static class ViewHolder {
		ImageView ivImagem;

		public ViewHolder (View v) {
			ivImagem = (ImageView) v.findViewById (R.id.iv_imagem);
		}
	}
}
