package com.example.smbat.develandooexample;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class UsersListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users_list);
        recyclerView = findViewById(R.id.users_list);
        final LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setHasFixedSize(true);
        new RetrieveData().execute();
    }

    private class RetrieveData extends AsyncTask<Void, Void, List<User>> {

        @Override
        protected List<User> doInBackground(Void... voids) {
            return Utils.getUsersList();
        }

        @Override
        protected void onPostExecute(List<User> users) {
            super.onPostExecute(users);
            populateRecyclerView(users);
        }
    }

    private void populateRecyclerView(List<User> users) {
        final UsersAdapter adapter = new UsersAdapter(this, users);
        recyclerView.setAdapter(adapter);
    }
}
