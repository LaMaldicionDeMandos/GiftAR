package org.byp.games.giftar.activities;

import android.content.ContentProvider;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Entity;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.plus.People;
import com.google.android.gms.plus.Plus;
import com.google.android.gms.plus.model.people.PersonBuffer;
import com.google.common.base.Joiner;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.google.inject.Inject;

import org.byp.games.giftar.R;
import org.byp.games.giftar.services.PreferencesService;

import java.util.List;

import roboguice.activity.RoboActionBarActivity;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;

import static org.byp.games.giftar.GiftARApplication.USER_CONTACTS_KEY;
import static org.byp.games.giftar.activities.ActivityUtils.getGoogleClient;

@ContentView(R.layout.activity_main)
public class MainActivity extends RoboActionBarActivity implements GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener{
    private final static String TAG = MainActivity.class.getSimpleName();
    @InjectView(R.id.contacts)
    private RecyclerView listView;

    private GoogleApiClient googleClient;

    @Inject
    private PreferencesService preferences;

    private final List<String> contacts = Lists.newArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        googleClient = getGoogleClient(this, this, this);
        setupToolbar();
        if (!preferences.contain(USER_CONTACTS_KEY)) {
            showImportContactsDialog();
        }
        Log.d(TAG, contacts.toString());
        RecyclerView.Adapter<ContactsAdapter.ViewHolder> adapter = new ContactsAdapter(contacts);
        listView.setLayoutManager(new LinearLayoutManager(this));
        listView.setAdapter(adapter);
    }

    private void setupToolbar(){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Show menu icon
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.drawable.ic_gift_32);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    private void showImportContactsDialog() {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this)
                .setTitle(R.string.add_contact_title)
                .setMessage(R.string.add_contact_message)
                .setCancelable(false)
                .setNegativeButton(R.string.disagree, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton(R.string.agree, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Log.d(TAG, "Agregando contactos");
                        importContacts();
                    }
                });
        alertBuilder.create().show();
    }

    private void importContacts() {
        Plus.PeopleApi.loadVisible(googleClient, null).setResultCallback(new ResultCallback<People.LoadPeopleResult>() {
            @Override
            public void onResult(People.LoadPeopleResult result) {
                if (result.getStatus().getStatusCode() == CommonStatusCodes.SUCCESS) {
                    PersonBuffer personBuffer = result.getPersonBuffer();
                    try {
                        int count = personBuffer.getCount();
                        for (int i = 0; i < count; i++) {
                            Log.d(TAG, "Display name: " + personBuffer.get(i).getDisplayName());
                        }
                    } finally {
                        personBuffer.release();
                    }
                } else {
                    Log.e(TAG, "Error requesting visible circles: " + result.getStatus());
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleClient.connect();
    }

    @Override
    protected void onStop() {
        super.onStop();
        googleClient.disconnect();
    }

    @Override
    public void onConnected(Bundle bundle) {
        Log.d(TAG, "Connected");
    }

    @Override
    public void onConnectionSuspended(int i) {
        Log.d(TAG, "Suspended?");
        finish();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        Log.d(TAG, "Connection Failed: " + connectionResult);
        finish();
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
