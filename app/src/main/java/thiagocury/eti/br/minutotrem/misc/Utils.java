package thiagocury.eti.br.minutotrem.misc;

import android.content.res.Resources;
import android.text.Html;
import android.text.Spanned;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import thiagocury.eti.br.minutotrem.R;

/***
 * @author Leandro Soares Quevedo
 * @version 1.0
 * @since 07/08/16
 ***/
public final class Utils {

	public static final String STATUS_URL = "http://trensurb.gov.br/paginas/operacoes.php";

	public static int calcularTempoEstimado (Spinner estacaoA, Spinner estacaoB) {
		int a = buscarTempo (estacaoA.getSelectedItemPosition ());
		int b = buscarTempo (estacaoB.getSelectedItemPosition ());

		return Math.abs (b - a);
	}

	private static int buscarTempo (int n) {
		switch (n) {
			case 1:
				return 0;
			case 2:
				return 2;
			case 3:
				return 4;
			case 4:
				return 7;
			case 5:
				return 10;
			case 6:
				return 11;
			case 7:
				return 14;
			case 8:
				return 16;
			case 9:
				return 19;
			case 10:
				return 21;
			case 11:
				return 23;
			case 12:
				return 25;
			case 13:
				return 28;
			case 14:
				return 31;
			case 15:
				return 34;
			case 16:
				return 38;
			case 17:
				return 41;
			case 18:
				return 44;
			case 19:
				return 46;
			case 20:
				return 48;
			case 21:
				return 50;
			case 22:
				return 53;
			default:
				return 0;
		}
	}

	/**
	 * Formata uma String dentro dos resources do app, aplicando valores de fora e gerando HTML
	 *
	 * @return HTML da entrada formatada
	 */
	private static Spanned formatarEntrada (Resources r, int idFormato, String valor) {
		return Html.fromHtml (String.format (r.getString (idFormato), valor));
	}

	public static void mostrarPrevisaoDeNormalizacao (TextView destino, Resources r, Operacao op) {
		if (op.getTemPrevisaoNormalizacao ()) {
			String horario = op.getHorarioPrevisaoNormalizacao ().toString ().substring (0, 5);
			destino.setText (formatarEntrada (r, R.string.formato_previsao_normalizacao, horario));
		} else {
			destino.setText ("");
		}
	}

	public static void mostrarIntervaloEntreTrens (TextView destino, Resources r, Operacao op) {
		if (op.getIntervaloEntreTrens () != null) {
			String intervalo = op.getIntervaloEntreTrens ().toString ();
			destino.setText (formatarEntrada (r, R.string.formato_intervalo_entre_trens, intervalo));
		} else {
			destino.setText ("");
		}
	}

	public static void mostrarIntervalos (TextView destino, Resources r, Operacao op) {
		if (op.getIntervalos () != null) {
			//Pega o formato do cabeçalho
			String cabecalho = r.getString (R.string.formato_cabecalho_intervalos);
			//Pega o formato de cada item
			String item = r.getString (R.string.formato_item_intervalos);
			StringBuilder buffer = new StringBuilder (cabecalho);

			//Para cada intervalo especificado, Formata-lo de acordo com o formato(item) especificado
			for (Intervalo intervalo : op.getIntervalos ()) {
				buffer.append (String.format (item, intervalo.getEstacaoPartida (), intervalo.getEstacaoChegada (), intervalo.getIntervalo ()));
			}

			destino.setText (Html.fromHtml (buffer.toString ()));
		} else {
			destino.setText ("");
		}
	}

	/**
	 * Baixa do site o status atual das operações
	 *
	 * @return O status das operações em JSON
	 */
	public static String baixarStatus () throws Exception {
		URL servidor = new URL (STATUS_URL);
		//Abre uma conexão com o site, e pega o fluxo de dados
		BufferedReader temp = new BufferedReader (new InputStreamReader (servidor.openStream ()));
		StringBuilder buffer = new StringBuilder ();
		//Lê linha por linha a resposta do servidor
		String linha;
		while ((linha = temp.readLine ()) != null) {
			buffer.append (linha).append ("\n");
		}
		//Fecha o fluxo de dados
		temp.close ();
		//Retorna a resposta do servidor como String
		return buffer.toString ();
	}

	public static double calcularDistancia (LatLng pontoA, LatLng pontoB) {
		double lat1 = pontoA.latitude;
		double lat2 = pontoB.latitude;
		double lon1 = pontoA.longitude;
		double lon2 = pontoB.longitude;

		double dLat = Math.toRadians (lat2 - lat1);
		double dLon = Math.toRadians (lon2 - lon1);

		double a = Math.sin (dLat / 2) * Math.sin (dLat / 2) + Math.cos (Math.toRadians (lat1)) * Math.cos (Math.toRadians (lat2)) * Math.sin (dLon / 2) * Math.sin (dLon / 2);
		double c = 2 * Math.asin (Math.sqrt (a));

		return 6366000 * c;
	}
}
