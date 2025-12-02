package com.example.mobilethelp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobilethelp.R;
import com.example.mobilethelp.model.Interacao;
import java.util.List;

public class InteracaoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;

    private List<Interacao> interacoes;
    private final Integer currentUserId;

    public InteracaoAdapter(List<Interacao> interacoes, Integer currentUserId) {
        this.interacoes = interacoes;
        this.currentUserId = currentUserId;
    }

    @Override
    public int getItemViewType(int position) {
        // Compara o ID do usuário da interação com o ID do usuário logado
        if (interacoes.get(position).getIdUsuario().equals(currentUserId)) {
            return VIEW_TYPE_SENT;
        } else {
            return VIEW_TYPE_RECEIVED;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_SENT) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_interaction_sent, parent, false);
            return new SentMessageViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_interaction_received, parent, false);
            return new ReceivedMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Interacao interacao = interacoes.get(position);
        if (holder.getItemViewType() == VIEW_TYPE_SENT) {
            ((SentMessageViewHolder) holder).bind(interacao);
        } else {
            ((ReceivedMessageViewHolder) holder).bind(interacao);
        }
    }

    @Override
    public int getItemCount() {
        return interacoes.size();
    }

    private static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMessage, textViewTimestamp;
        SentMessageViewHolder(View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.textViewMessage);
            textViewTimestamp = itemView.findViewById(R.id.textViewTimestamp);
        }
        void bind(Interacao interacao) {
            textViewMessage.setText(interacao.getMensagem());
            textViewTimestamp.setText(interacao.getDataHora());
        }
    }

    private static class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAuthor, textViewMessage, textViewTimestamp;
        ReceivedMessageViewHolder(View itemView) {
            super(itemView);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewMessage = itemView.findViewById(R.id.textViewMessage);
            textViewTimestamp = itemView.findViewById(R.id.textViewTimestamp);
        }
        void bind(Interacao interacao) {
            // Como não temos mais o nome do autor, podemos esconder o campo ou mostrar o ID
            textViewAuthor.setText("Usuário: " + interacao.getIdUsuario());
            textViewMessage.setText(interacao.getMensagem());
            textViewTimestamp.setText(interacao.getDataHora());
        }
    }
}