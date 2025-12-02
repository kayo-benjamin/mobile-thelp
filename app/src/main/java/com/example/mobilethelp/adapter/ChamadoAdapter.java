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

public class ChamadoAdapter extends RecyclerView.Adapter<ChamadoAdapter.ChamadoViewHolder> {

    private List<Chamado> chamados;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(Chamado chamado);
    }

    public ChamadoAdapter(List<Chamado> chamados, OnItemClickListener listener) {
        this.chamados = chamados;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ChamadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_chamado, parent, false);
        return new ChamadoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChamadoViewHolder holder, int position) {
        holder.bind(chamados.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return chamados.size();
    }

    static class ChamadoViewHolder extends RecyclerView.ViewHolder {
        TextView textViewTitle, textViewStatus, textViewDate;

        public ChamadoViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewTitle = itemView.findViewById(R.id.textViewChamadoTitle);
            textViewStatus = itemView.findViewById(R.id.textViewChamadoStatus);
            textViewDate = itemView.findViewById(R.id.textViewChamadoDate);
        }

        public void bind(final Chamado chamado, final OnItemClickListener listener) {
            textViewTitle.setText(chamado.getTitulo());
            textViewStatus.setText("Status: " + chamado.getStatus());
            textViewDate.setText("Data: " + chamado.getDataAbertura());
            itemView.setOnClickListener(v -> listener.onItemClick(chamado));
        }
    }
}