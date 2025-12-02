package com.example.mobilethelp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobilethelp.R;
import com.example.mobilethelp.model.Chamado;
import java.util.List;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.TicketViewHolder> {

    private List<Chamado> chamados;
    private OnTicketClickListener listener;

    public interface OnTicketClickListener {
        void onTicketClick(Chamado chamado);
    }

    public TicketAdapter(List<Chamado> chamados, OnTicketClickListener listener) {
        this.chamados = chamados;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TicketViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ticket, parent, false);
        return new TicketViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketViewHolder holder, int position) {
        Chamado chamado = chamados.get(position);
        holder.bind(chamado, listener);
    }

    @Override
    public int getItemCount() {
        return chamados.size();
    }

    static class TicketViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewStatus, textViewDate;

        public TicketViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewTicketTitle);
            textViewStatus = itemView.findViewById(R.id.textViewTicketStatus);
            textViewDate = itemView.findViewById(R.id.textViewTicketDate);
        }

        public void bind(final Chamado chamado, final OnTicketClickListener listener) {
            textViewTitle.setText(chamado.getTitulo());
            textViewStatus.setText("Status: " + chamado.getStatus());
            textViewDate.setText("Data: " + chamado.getDataAbertura());
            itemView.setOnClickListener(v -> listener.onTicketClick(chamado));
        }
    }
}