package ladis.rma_projekt;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

class CountriesAdapter extends RecyclerView.Adapter<CountriesAdapter.CustomViewHolder> {
    private LayoutInflater layoutInflater;
    private List<Country> countries;
    private ItemClickInterface itemClickInterface;

    public CountriesAdapter(Context context, List<Country> countries) {
        this.layoutInflater = LayoutInflater.from(context);
        this.countries = countries;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.country_row, viewGroup, false);

        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder customViewHolder, int i) {
        Country country = countries.get(i);
        customViewHolder.countryName.setText(country.getName());
    }

    @Override
    public int getItemCount() {
        return countries == null ? 0 : countries.size();
    }

    public void setItemClickInterface(ItemClickInterface itemClickInterface) {
        this.itemClickInterface = itemClickInterface;
    }

    public Country getCountry(int position){
        return countries.get(position);
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder{
        private TextView countryName;

        public CustomViewHolder(View view){
            super(view);

            countryName = view.findViewById(R.id.countryName);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemClickInterface.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
