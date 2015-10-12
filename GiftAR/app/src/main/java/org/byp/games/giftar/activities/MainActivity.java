package org.byp.games.giftar.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.byp.games.giftar.R;

import java.util.List;

import roboguice.activity.RoboActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

;import static com.google.common.collect.Lists.newArrayList;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActivity {
    private final static String TAG = MainActivity.class.getSimpleName();
    @InjectView(R.id.contacts)
    private RecyclerView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "Agregando contactos: Aida y marcelo");
        List<String> contacts = newArrayList(
                "Aída0",
                "Aída1",
                "Aída2",
                "Aída3",
                "Aída4",
                "Aída5",
                "Aída6",
                "Aída7",
                "Aída8",
                "Aída9",
                "Aída10",
                "Aída11",
                "Aída12",
                "Aída13",
                "Aída14",
                "Aída15",
                "Aída16",
                "Aída17",
                "Aída18",
                "Aída19",
                "Marcelo"
                );
        RecyclerView.Adapter<ContactsAdapter.ViewHolder> adapter = new ContactsAdapter(contacts);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adapter);
    }

    class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder> {
        private final List<String> contacts;

        public ContactsAdapter(final List<String> contacts) {
            this.contacts = contacts;
        }
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(MainActivity.this)
                    .inflate(android.R.layout.simple_list_item_1, viewGroup, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder contactViewHolder, int i) {
            contactViewHolder.name.setText(contacts.get(i));
        }

        @Override
        public int getItemCount() {
            return contacts.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            private final TextView name;

            public ViewHolder(View itemView) {
                super(itemView);
                name = (TextView)itemView.findViewById(android.R.id.text1);
            }
        }
    }

}
