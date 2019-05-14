package thiagocury.eti.br.minutotrem;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import thiagocury.eti.br.minutotrem.misc.Estacao;
import thiagocury.eti.br.minutotrem.misc.EstacaoAdapter;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragEstacoes extends Fragment {

    private RecyclerView rvEstacoes;

    private ArrayList<Estacao> ests = null;

    private EstacaoAdapter adapter;

    public FragEstacoes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_frag_estacoes, container, false);

        rvEstacoes = view.findViewById(R.id.frag_estacoes_rv_estacoes);

        ests = new ArrayList<>();
        ests = TelaInicial.ests;

        adapter = new EstacaoAdapter(getActivity().getApplicationContext(), ests);

        rvEstacoes.setAdapter(adapter);
        rvEstacoes.setHasFixedSize(true);
        rvEstacoes.setLayoutManager(new LinearLayoutManager(getActivity()));

        adapter.setOnItemClickListener(new EstacaoAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

            }

            @Override
            public void onItemLongClick(int position, View v) {

            }
        });

        return view;
    }

}
