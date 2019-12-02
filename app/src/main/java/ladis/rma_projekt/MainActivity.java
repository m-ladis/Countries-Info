package ladis.rma_projekt;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ItemClickInterface {
    private RecyclerView countriesRecyclerView;
    private RESTTask restTask;
    private CountriesAdapter countriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countriesRecyclerView = findViewById(R.id.countriesRecyclerView);

        countriesRecyclerView.setHasFixedSize(true);
        countriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        restTask = new RESTTask(this);
        restTask.execute(getString(R.string.api_url));
    }

    @Override
    protected void onStop() {
        super.onStop();

        restTask.cancel(true);
    }

    @Override
    public void onItemClick(int position) {
        Country selectedCountry = countriesAdapter.getCountry(position);

        Intent intent = new Intent(this, CountryDetails.class);
        intent.putExtra("selectedCountry", selectedCountry);

        startActivity(intent);
    }

    private static class RESTTask extends AsyncTask<String, Void, List<Country>> {

        private WeakReference<MainActivity> activity;

        public RESTTask(MainActivity activity){
            this.activity = new WeakReference<>(activity);
        }

        @Override
        protected List<Country> doInBackground(String... strings) {
            MainActivity mainActivity = activity.get();


            String adresa = strings[0];
            try {
                URL url = new URL(adresa);

                HttpURLConnection connection =
                        (HttpURLConnection)url.openConnection();

                connection.setRequestMethod("GET");
                connection.setRequestProperty("x-rapidapi-host",
                        mainActivity.getString(R.string.api_host));

                connection.setRequestProperty("x-rapidapi-key",
                        mainActivity.getString(R.string.api_key));

                connection.setReadTimeout(5000);
                connection.setConnectTimeout(5000);
                connection.connect();
                InputStreamReader streamReader =
                        new InputStreamReader(connection.getInputStream());

                BufferedReader reader =
                        new BufferedReader(streamReader);

                String response = "{\"countries\":" + reader.readLine() + "}";

                CountryApiResponse apiResponse = new Gson().
                        fromJson(response, CountryApiResponse.class);

                reader.close();
                streamReader.close();

                return apiResponse.getCountries();

            }catch(Exception e){
                Log.d("GRESKA", e.getMessage());
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {

        }

        @Override
        protected void onPostExecute(List<Country> countries) {
            MainActivity mainActivity = activity.get();

            mainActivity.countriesAdapter = new CountriesAdapter(mainActivity, countries);
            mainActivity.countriesAdapter.setItemClickInterface(mainActivity);
            mainActivity.countriesRecyclerView.setAdapter(mainActivity.countriesAdapter);
            mainActivity.countriesAdapter.notifyDataSetChanged();
        }
    }
}
