package ladis.rma_projekt;

import java.util.ArrayList;

class CountryApiResponse {
    private ArrayList<Country> countries=null;

    public ArrayList<Country> getCountries() {
        return countries;
    }

    public void setCountries(ArrayList<Country> countries) {
        this.countries = countries;
    }
}
