package thiagocury.eti.br.minutotrem.misc;

import java.util.ArrayList;

import thiagocury.eti.br.minutotrem.R;

/**
 * Created by thiagocury on 08/09/15.
 */
public class Etiqueta {

	private int imagem;

	public Etiqueta () {
	}

	public Etiqueta (int imagem) {
		this.imagem = imagem;
	}

	public int getImagem () {
		return imagem;
	}

	public void setImagem (int imagem) {
		this.imagem = imagem;
	}

	public static ArrayList<Etiqueta> getEtiquetas () {
		ArrayList<Etiqueta> ets = new ArrayList<> ();
		ets.add (new Etiqueta (R.drawable.ic_et1));
		ets.add (new Etiqueta (R.drawable.ic_et2));
		ets.add (new Etiqueta (R.drawable.ic_et3));
		ets.add (new Etiqueta (R.drawable.ic_et4));
		ets.add (new Etiqueta (R.drawable.ic_et5));
		ets.add (new Etiqueta (R.drawable.ic_et6));
		ets.add (new Etiqueta (R.drawable.ic_et7));
		ets.add (new Etiqueta (R.drawable.ic_et8));
		ets.add (new Etiqueta (R.drawable.ic_et9));
		ets.add (new Etiqueta (R.drawable.ic_et10));
		ets.add (new Etiqueta (R.drawable.ic_et11));
		ets.add (new Etiqueta (R.drawable.ic_et12));

		return ets;
	}

	@Override
	public String toString () {
		final StringBuffer sb = new StringBuffer ();
		sb.append ("imagem=").append (imagem);
		return sb.toString ();
	}
}