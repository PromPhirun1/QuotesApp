package com.promphirun.quotesapp;

import android.app.ProgressDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.List;

public class MainActivity extends AppCompatActivity implements CopyListener{
        RecyclerView recycler_home;
        RequestManager manager;
        QuotesRecyclerAdapter adapter;
        ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycler_home = findViewById(R.id.recycler_home);

        manager = new RequestManager(this);
        manager.GetAllQuotes(listener);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading....");
        dialog.show();
    }
    private final QuoteResponseListener listener = new QuoteResponseListener() {
        @Override
        public void didFetch(List<QuotesResponse> responses, String message) {
            showData(responses);
            dialog.dismiss();
        }

        @Override
        public void didError(String message) {
            dialog.dismiss();
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private void showData(List<QuotesResponse> responses) {
        recycler_home.setHasFixedSize(true);
        recycler_home.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        adapter = new QuotesRecyclerAdapter(MainActivity.this, responses, this);
        recycler_home.setAdapter(adapter);
    }

    @Override
    public void onCopyClicked(String text) {
        ClipboardManager clipboardManager = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData data = ClipData.newPlainText("Copies data", text);
        clipboardManager.setPrimaryClip(data);
        Toast.makeText(MainActivity.this,"Copied to Clipboard!!!" , Toast.LENGTH_SHORT).show();
    }
}