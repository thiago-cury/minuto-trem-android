package thiagocury.eti.br.minutotrem.misc;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import thiagocury.eti.br.minutotrem.R;
import thiagocury.eti.br.minutotrem.misc.Ciclista;

/***
 * @author Leandro Soares Quevedo
 * @version 1.0
 * @since 11/09/16
 ***/
public class CiclistaAdapter extends BaseAdapter {

	private Context context;
	private ArrayList<Ciclista> ciclistas;
	private LayoutInflater inflater;

	public CiclistaAdapter (Context context, ArrayList<Ciclista> ciclistas) {
		this.context = context;
		this.ciclistas = ciclistas;
		this.inflater = LayoutInflater.from (context);
	}

	@Override
	public int getCount () {
		return ciclistas.size ();
	}

	@Override
	public Object getItem (int i) {
		return ciclistas.get (i);
	}

	@Override
	public long getItemId (int i) {
		return i;
	}

	@Override
	public View getView (int i, View view, ViewGroup viewGroup) {
		ViewHolder holder;

		if (view == null) {
			view = inflater.inflate (R.layout.linha_ciclista, null);
			holder = new ViewHolder (view);
			view.setTag (holder);
		} else {
			holder = (ViewHolder) view.getTag ();
		}

		Ciclista ciclista = (Ciclista) getItem (i);

		Picasso	.with (context).load (ciclista.getImagem ())
				.placeholder (R.mipmap.ic_launcher)
				.into (holder.ivImagem);

		holder.tvDescricao.setText (ciclista.getDescricao ());
		holder.tvCategoria.setText (ciclista.getCategoria ());

		return view;
	}

	static class ViewHolder {

		ImageView ivImagem;
		TextView tvCategoria;
		TextView tvDescricao;

		public ViewHolder (View v) {
			ivImagem = (ImageView) v.findViewById (R.id.iv_imagem);
			tvDescricao = (TextView) v.findViewById (R.id.tv_descricao);
			tvCategoria = (TextView) v.findViewById (R.id.tv_categoria);
		}

	}

}
