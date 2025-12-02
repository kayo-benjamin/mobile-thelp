package com.example.mobilethelp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobilethelp.R;
import com.example.mobilethelp.model.Interaction;
import java.util.List;

public class InteractionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;

    private List<Interaction> interactions;

    public InteractionAdapter(List<Interaction> interactions) {
        this.interactions = interactions;
    }

    @Override
    public int getItemViewType(int position) {
        if (interactions.get(position).getAuthor().equals("User")) {
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
        Interaction interaction = interactions.get(position);
        if (holder.getItemViewType() == VIEW_TYPE_SENT) {
            ((SentMessageViewHolder) holder).bind(interaction);
        } else {
            ((ReceivedMessageViewHolder) holder).bind(interaction);
        }
    }

    @Override
    public int getItemCount() {
        return interactions.size();
    }

    private class SentMessageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMessage, textViewTimestamp;

        SentMessageViewHolder(View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.textViewMessage);
            textViewTimestamp = itemView.findViewById(R.id.textViewTimestamp);
        }

        void bind(Interaction interaction) {
            textViewMessage.setText(interaction.getMessage());
            textViewTimestamp.setText(interaction.getTimestamp());
        }
    }

    private class ReceivedMessageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewAuthor, textViewMessage, textViewTimestamp;

        ReceivedMessageViewHolder(View itemView) {
            super(itemView);
            textViewAuthor = itemView.findViewById(R.id.textViewAuthor);
            textViewMessage = itemView.findViewById(R.id.textViewMessage);
            textViewTimestamp = itemView.findViewById(R.id.textViewTimestamp);
        }

        void bind(Interaction interaction) {
            textViewAuthor.setText(interaction.getAuthor());
            textViewMessage.setText(interaction.getMessage());
            textViewTimestamp.setText(interaction.getTimestamp());
        }
    }
}