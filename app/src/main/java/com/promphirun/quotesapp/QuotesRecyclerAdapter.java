package com.promphirun.quotesapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuotesRecyclerAdapter extends RecyclerView.Adapter<QuotesViewHolder>{
    Context context;
    List<QuotesResponse> list;
    CopyListener listener;

    public QuotesRecyclerAdapter(Context context, List<QuotesResponse> list, CopyListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public QuotesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new QuotesViewHolder(LayoutInflater.from(context).inflate(R.layout.list_quote, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull QuotesViewHolder holder, int position) {
        holder.textView_quote.setText(list.get(position).getText());
        holder.textView_author.setText(list.get(position).getAuthor());
        holder.button_copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               listener.onCopyClicked(list.get(holder.getAdapterPosition()).getText());
            }
        });
    }

    @Override
    public int getItemCount() {

        return list.size();
    }
}
class QuotesViewHolder extends RecyclerView.ViewHolder {
    TextView textView_quote, textView_author;
    Button button_copy;
    public QuotesViewHolder(@NonNull View itemView) {
        super(itemView);
        textView_quote = itemView.findViewById(R.id.textView_quote);
        textView_author = itemView.findViewById(R.id.textView_author);
        button_copy = itemView.findViewById(R.id.button_copy);
    }
}
