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

    private static final int VIEW_TYPE_SENT = 1; // Enviado pelo "User"
    private static final int VIEW_TYPE_RECEIVED = 2; // Recebido do "Support" ou outro
    private final String currentUserAuthorName = "User";

    private List<Interacao> interacoes;

    // O construtor correto que estava faltando
    public InteracaoAdapter(List<Interacao> interacoes) {
        this.interacoes = interacoes;
    }

    @Override
    public int getItemViewType(int position) {
        if (interacoes.get(position).getAutor().equals(currentUserAuthorName)) {
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

    // ViewHolders internos
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
            textViewAuthor.setText(interacao.getAutor());
            textViewMessage.setText(interacao.getMensagem());
            textViewTimestamp.setText(interacao.getDataHora());
        }
    }
}