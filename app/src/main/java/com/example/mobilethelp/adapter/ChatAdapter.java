package com.example.mobilethelp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mobilethelp.R;
import com.example.mobilethelp.model.ChatMessage;
import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_SENT = 1;
    private static final int VIEW_TYPE_RECEIVED = 2;
    private final String currentUserAuthorName; // Para identificar o autor atual

    private List<ChatMessage> messages;

    public ChatAdapter(List<ChatMessage> messages, String currentUserAuthorName) {
        this.messages = messages;
        this.currentUserAuthorName = currentUserAuthorName;
    }

    @Override
    public int getItemViewType(int position) {
        // A API deve retornar o nome do autor. Comparamos para ver se é o usuário atual.
        if (messages.get(position).getAutor().equals(currentUserAuthorName)) {
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
        } else { // VIEW_TYPE_RECEIVED
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_interaction_received, parent, false);
            return new ReceivedMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ChatMessage message = messages.get(position);
        if (holder.getItemViewType() == VIEW_TYPE_SENT) {
            ((SentMessageViewHolder) holder).bind(message);
        } else {
            ((ReceivedMessageViewHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    private static class SentMessageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMessage, textViewTimestamp;

        SentMessageViewHolder(View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.textViewMessage);
            textViewTimestamp = itemView.findViewById(R.id.textViewTimestamp);
        }

        void bind(ChatMessage message) {
            textViewMessage.setText(message.getMensagem());
            textViewTimestamp.setText(message.getDataHora());
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

        void bind(ChatMessage message) {
            textViewAuthor.setText(message.getAutor());
            textViewMessage.setText(message.getMensagem());
            textViewTimestamp.setText(message.getDataHora());
        }
    }
}