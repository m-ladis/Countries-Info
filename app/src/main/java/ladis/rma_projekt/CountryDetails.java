package ladis.rma_projekt;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class CountryDetails extends AppCompatActivity implements View.OnClickListener {

    private Button backButton;
    private TextView name;
    private TextView capital;
    private TextView relevance;
    private TextView region;
    private TextView subregion;
    private TextView population;
    private TextView demonym;
    private TextView area;
    private LinearLayout timezones;
    private LinearLayout callingCodes;
    private LinearLayout topLevelDomain;
    private TextView alpha2Code;
    private TextView alpha3Code;
    private LinearLayout currencies;
    private LinearLayout languages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_details);

        Intent intent = getIntent();
        Country country = (Country) intent.getSerializableExtra("selectedCountry");

        backButton = findViewById(R.id.backButton);
        name = findViewById(R.id.name);
        capital = findViewById(R.id.capital);
        relevance = findViewById(R.id.relevance);
        region = findViewById(R.id.region);
        subregion = findViewById(R.id.subregion);
        population = findViewById(R.id.population);
        demonym = findViewById(R.id.demonym);
        area = findViewById(R.id.area);
        timezones = findViewById(R.id.timezones);
        callingCodes = findViewById(R.id.callingCodes);
        topLevelDomain = findViewById(R.id.topLevelDomain);
        alpha2Code = findViewById(R.id.alpha2Code);
        alpha3Code = findViewById(R.id.alpha3Code);
        currencies = findViewById(R.id.currencies);
        languages = findViewById(R.id.languages);

        backButton.setOnClickListener(this);

        updateUI(country);
    }

    private void updateUI(Country country) {
        name.setText(country.getName());
        capital.setText(country.getCapital());
        relevance.setText(country.getRelevance());
        region.setText(country.getRegion());
        subregion.setText(country.getSubregion());
        population.setText(String.valueOf(country.getPopulation()));
        demonym.setText(country.getDemonym());
        area.setText(String.valueOf(country.getArea()));

        for(String timezone:country.getTimezones()){
            TextView textView = new TextView(this);
            textView.setText(timezone);
            timezones.addView(textView);
        }

        for(String callingCode:country.getCallingCodes()){
            TextView textView = new TextView(this);
            textView.setText(callingCode);
            callingCodes.addView(textView);
        }

        for(String topLevel:country.getTopLevelDomain()){
            TextView textView = new TextView(this);
            textView.setText(topLevel);
            topLevelDomain.addView(textView);
        }

        alpha2Code.setText(String.valueOf(country.getAlpha2Code()));
        alpha3Code.setText(String.valueOf(country.getAlpha3Code()));

        for(String currency:country.getCurrencies()){
            TextView textView = new TextView(this);
            textView.setText(currency);
            currencies.addView(textView);
        }

        for(String language:country.getLanguages()){
            TextView textView = new TextView(this);
            textView.setText(language);
            languages.addView(textView);
        }
    }

    @Override
    public void onClick(View v) {
        if(v == backButton){
            finish();
        }
    }
}
