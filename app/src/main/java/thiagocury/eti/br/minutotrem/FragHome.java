package thiagocury.eti.br.minutotrem;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import thiagocury.eti.br.minutotrem.misc.Operacao;
import thiagocury.eti.br.minutotrem.misc.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragHome extends Fragment {

	private final String URL_IMAGEM_OPERACAO_NORMAL = "http://www.trensurb.gov.br/imagens/status_img_operacao_normal.png";
	private final String URL_IMAGEM_OPERACAO_PARCIAL = "http://www.trensurb.gov.br/imagens/status_img_operacao_parcial.png";
	private final String URL_IMAGEM_OPERACAO_PARALIZADA = "http://www.trensurb.gov.br/imagens/status_img_operacao_paralizada.png";
	private final String URL_IMAGEM_OPERACAO_COM_ALTERACAO = "http://www.trensurb.gov.br/imagens/status_img_com_alteracao.png";

	private TextView tvBoasVindas;

	private ImageView ivStatusImagem;
	private TextView tvStatusOperacao;
	private TextView tvHorarioPrevisto;
	private TextView tvIntervalo;
	private Button btnAtualizarStatus;

	private Spinner spEstacaoPartida;
	private Spinner spEstacaoChegada;
	private TextView tvCalculo;

	private Operacao operacao;
	private String ultimoErro;
	private Dialog ultimoDialogo = null;

	private AdView mAdView;


	public FragHome () {
		// Required empty public constructor
	}

	@Override
	public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		//Inflando a View do Fragment. Dessa forma conseguimos pegar as referências.
		View v = inflater.inflate (R.layout.fragment_frag_home, container, false);

		//Buscando a referência
		tvBoasVindas = (TextView) v.findViewById (R.id.frag_home_tv_boas_vindas);
		ivStatusImagem = (ImageView) v.findViewById (R.id.frag_home_status_imagem);
		tvStatusOperacao = (TextView) v.findViewById (R.id.frag_home_status_operacao);
		tvHorarioPrevisto = (TextView) v.findViewById (R.id.frag_home_tv_horario_previsto);
		tvIntervalo = (TextView) v.findViewById (R.id.frag_home_tv_intervalo);
		btnAtualizarStatus = (Button) v.findViewById (R.id.frag_home_btn_atualizar_status);

		spEstacaoPartida = (Spinner) v.findViewById (R.id.frag_home_sp_estacao_partida);
		spEstacaoChegada = (Spinner) v.findViewById (R.id.frag_home_sp_estacao_chegada);
		tvCalculo = (TextView) v.findViewById (R.id.frag_home_tv_calculo);

		MobileAds.initialize(getActivity().getApplication(), "ca-app-pub-2051234629138297~1110047092");

		mAdView = v.findViewById(R.id.frag_home_adview);
		AdRequest adRequest = new AdRequest.Builder().build();
		mAdView.loadAd(adRequest);

		AdapterView.OnItemSelectedListener onItemSelectedListener = new AdapterView.OnItemSelectedListener () {
			@Override
			public void onItemSelected (AdapterView<?> adapterView, View view, int i, long l) {
				calcularTempoEstimado ();
			}

			@Override
			public void onNothingSelected (AdapterView<?> adapterView) {
				calcularTempoEstimado ();
			}
		};
		spEstacaoPartida.setOnItemSelectedListener (onItemSelectedListener);
		spEstacaoChegada.setOnItemSelectedListener (onItemSelectedListener);

		btnAtualizarStatus.setOnClickListener (new View.OnClickListener () {
			@Override
			public void onClick (View view) {
				atualizarStatus ();
			}
		});


		//Buscando o contexto para a Toast
		/*Toast.makeText(
                getActivity().getBaseContext(),
                "Olá, seja bem vindo!",
                Toast.LENGTH_SHORT).show();*/

		// Inflate the layout for this fragment
		atualizarStatus ();
		return v;
	}//fecha onCreateView

	@Override
	public void onPause () {
		super.onPause ();
		if (ultimoDialogo != null) ultimoDialogo.dismiss ();
	}

	private void calcularTempoEstimado () {
		if (spEstacaoPartida.getSelectedItemPosition () != 0 && spEstacaoChegada.getSelectedItemPosition () != 0) {
			tvCalculo.setText (Utils.calcularTempoEstimado (spEstacaoPartida, spEstacaoChegada) + " min");
		} else {
			tvCalculo.setText ("");
		}
	}

	private void atualizarStatus () {
		btnAtualizarStatus.setEnabled (false);
		Thread t = new Thread (new Runnable () {
			@Override
			public void run () {
				operacao = null;
				try {
					Gson gson = new Gson ();
					String dados = Utils.baixarStatus ();
					operacao = gson.fromJson (dados, Operacao.class);
					if (operacao != null) atualizarInterface ();
				} catch (Exception e) {
					ultimoErro = e.getMessage () == null ? "Erro não definido" : e.getMessage ();
					mostrarErro ();
				}
			}
		});
		t.start ();
	}

	private void atualizarInterface () {
		getActivity ().runOnUiThread (new Runnable () {
			@Override
			public void run () {
				int situacao = operacao.getStatusSituacaoOperacional ();
				switch (situacao) {
					case 1: //Operação normal
						Picasso.with (getActivity ()).load (URL_IMAGEM_OPERACAO_NORMAL).placeholder (R.mipmap.ic_launcher).into (ivStatusImagem);
						tvStatusOperacao.setText ("OPERAÇÃO NORMAL");
						tvHorarioPrevisto.setText ("");
						Utils.mostrarIntervalos (tvIntervalo, getResources (), operacao);
						break;
					case 2: //Circulação parcial
						Picasso.with (getActivity ()).load (URL_IMAGEM_OPERACAO_PARCIAL).placeholder (R.mipmap.ic_launcher).into (ivStatusImagem);
						tvStatusOperacao.setText ("CIRCULAÇÂO PARCIAL");
						Utils.mostrarIntervaloEntreTrens (tvIntervalo, getResources (), operacao);
						Utils.mostrarPrevisaoDeNormalizacao (tvHorarioPrevisto, getResources (), operacao);
						break;
					case 3: //Paralisação total
						Picasso.with (getActivity ()).load (URL_IMAGEM_OPERACAO_PARALIZADA).placeholder (R.mipmap.ic_launcher).into (ivStatusImagem);
						tvStatusOperacao.setText ("PARALISAÇÂO TOTAL");
						Utils.mostrarIntervaloEntreTrens (tvIntervalo, getResources (), operacao);
						Utils.mostrarPrevisaoDeNormalizacao (tvHorarioPrevisto, getResources (), operacao);
						break;
					case 4: //Operação com alteração
						Picasso.with (getActivity ()).load (URL_IMAGEM_OPERACAO_COM_ALTERACAO).placeholder (R.mipmap.ic_launcher).into (ivStatusImagem);
						tvStatusOperacao.setText ("OPERAÇÃO COM ALTERAÇÃO");
						Utils.mostrarIntervaloEntreTrens (tvIntervalo, getResources (), operacao);
						Utils.mostrarPrevisaoDeNormalizacao (tvHorarioPrevisto, getResources (), operacao);
						break;
				}

				btnAtualizarStatus.setEnabled (true);
			}
		});
	}

	private void mostrarErro () {
		if (getActivity () == null) return;//Para poder sair, caso o app seja reaberto
		getActivity ().runOnUiThread (new Runnable () {
			@Override
			public void run () {
				Log.e ("MINUTO_TREM", ultimoErro);
				AlertDialog.Builder alerta = new AlertDialog.Builder (getActivity ());
				alerta.setTitle (getResources ().getString (R.string.aviso_geral_erro));
				alerta.setMessage (getResources ().getString (R.string.aviso_erro_internet));
				alerta.setIcon (R.drawable.error);
				alerta.setNeutralButton ("OK", null);
				ultimoDialogo = alerta.show ();
				btnAtualizarStatus.setEnabled (true);
			}
		});
	}

}//fecha FragHome