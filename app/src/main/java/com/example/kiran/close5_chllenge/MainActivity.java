package com.example.kiran.close5_chllenge;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kiran.close5_chllenge.model.Districts;
import com.example.kiran.close5_chllenge.parsers.DistrictsJSONParser;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String URL = "https://data.sfgov.org/resource/ritf-b9ki.json";
    ListView lv;
    ProgressDialog mProgressDialog;
    MyTask task;
    ArrayList<Districts> districtList = new ArrayList<Districts>();
    ArrayList<LatLng> location = new ArrayList<LatLng>();
    ArrayList<String> category = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv = (ListView) findViewById(R.id.list);
        mProgressDialog = new ProgressDialog(this);

        if (isOnline()) {
            requestData(URL);
        } else {
            Toast.makeText(getBaseContext(), "Network isn't available", Toast.LENGTH_LONG).show();
        }
    }
    private void requestData(String uri) {
        task = new MyTask();
        mProgressDialog.setMessage("Loading Data");
        mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        mProgressDialog.setIndeterminate(true);
        mProgressDialog = mProgressDialog.show(
                MainActivity.this,
                "Dialog",
                "Loading...",
                true,
                true,
                new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialog) {
                        if (task != null && task.isCancelled() == false)
                            task.cancel(true);

                    }
                }
        );
        task.execute(uri);
    }
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }
    protected void updateDisplay() {
        DistrictsArrayAdapter adapter = new DistrictsArrayAdapter(this, R.layout.item_district, districtList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(itemclicklistenter);
    }
    AdapterView.OnItemClickListener itemclicklistenter = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Districts district = districtList.get(position);
            Intent intent = new Intent(MainActivity.this, MapsActivity.class);
            intent.putExtra("LATITUDE", district.getLatitude());
            intent.putExtra("LONGITUDE", district.getLongitude());
            intent.putExtra("ADDRESS", district.getAddress());
            intent.putExtra("CATEGORY", district.getCategory());
            startActivity(intent);
        }
    };



    private class MyTask extends AsyncTask<String, String, ArrayList<Districts>> {

        @Override
        protected void onPreExecute() {


        }

        @Override
        protected ArrayList<Districts> doInBackground(String... params) {

            //Log.d("KIRAN", "URL: " + params[0]);
            String content = HttpManager.getData(params[0]);
            //Log.d("KIRAN", "content: " + content);
            districtList = DistrictsJSONParser.parseFeed(content);
            //Log.d("KIRAN", "doInBackground: " + districtList);
            return districtList;
        }

        @Override
        protected void onPostExecute(ArrayList<Districts> result) {
            if (result == null) {
                Toast.makeText(getBaseContext(), "Web service not available", Toast.LENGTH_LONG).show();
                return;
            }
            mProgressDialog.dismiss();
            mProgressDialog.cancel();
            districtList = result;
            updateDisplay();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_list, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.mapview:
                //Log.d("KIRAN", "DISTRICT OBJECT: " + districtList );
                for(int i=0; i< districtList.size() ; i++){
                    Districts district = districtList.get(i);
                    LatLng loc = new LatLng(Double.parseDouble(district.getLatitude()), Double.parseDouble(district.getLongitude()));
                    location.add(i, loc);
                    category.add(i,district.getCategory());
                    Log.d("KIRAN", "LATLONG: " + loc);
                }
                Intent intent = new Intent(MainActivity.this, MapsAllActivity.class);
                intent.putParcelableArrayListExtra("DISTRICTLIST", location);
                intent.putStringArrayListExtra("CATEGORY", category);
                startActivity(intent);
                break;

            default:
                break;
        }

        return true;
    }
}
