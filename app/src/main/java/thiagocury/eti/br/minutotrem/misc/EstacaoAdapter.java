package thiagocury.eti.br.minutotrem.misc;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import jp.wasabeef.picasso.transformations.GrayscaleTransformation;
import thiagocury.eti.br.minutotrem.R;

/**
 * Created by thiagocury on 24/01/2018.
 */

public class EstacaoAdapter extends RecyclerView.Adapter {

    private Context context;
    private ArrayList<Estacao> ests;
    private static ClickListener clickListener;

    public EstacaoAdapter(Context context, ArrayList<Estacao> Estacaos) {
        this.context = context;
        this.ests = Estacaos;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.linha_estacao, parent, false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder hold = (ViewHolder) holder;
        Estacao est = ests.get(position);

        hold.tvDescricao.setText(est.getDescricao());
        hold.tvCidade.setText(est.getCidade());
        hold.tvEndereco.setText(est.getEndereco());

        Picasso.with(context).load(est.getImagem())
                .into(((ViewHolder) holder).ivEstacao);

    }

    @Override
    public int getItemCount() {
        return ests.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final TextView tvDescricao;
        private final TextView tvCidade;
        private final TextView tvEndereco;
        private final ImageView ivEstacao;

        public ViewHolder(View v) {
            super(v);

            v.setOnClickListener(this);
            v.setOnLongClickListener(this);

            tvDescricao = (TextView) v.findViewById(R.id.le_tv_descricao);
            tvCidade = (TextView) v.findViewById(R.id.le_tv_cidade);
            tvEndereco = (TextView) v.findViewById(R.id.le_tv_endereco);
            ivEstacao = (ImageView) v.findViewById(R.id.le_iv_estacao);

        }

        @Override
        public void onClick(View view) {
            clickListener.onItemClick(getAdapterPosition(), view);
        }

        @Override
        public boolean onLongClick(View view) {
            clickListener.onItemLongClick(getAdapterPosition(), view);
            return true;
        }
    }

    public void setOnItemClickListener(ClickListener clickListener) {
        EstacaoAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onItemClick(int position, View v);
        void onItemLongClick(int position, View v);
    }
}
