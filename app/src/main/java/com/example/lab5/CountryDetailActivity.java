package com.example.lab5;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.lab5.Model.Country;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CountryDetailActivity extends AppCompatActivity {
    private TextView tvCountryName, tvRegion, tvCapital, tvPopulation, tvLanguages,tvMap,tvOfficial;
    private ImageView tvFlag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_country_detail);
        tvCountryName = findViewById(R.id.tvCountryName);
        tvRegion = findViewById(R.id.tvRegion);
        tvCapital = findViewById(R.id.tvCapital);
        tvPopulation = findViewById(R.id.tvPopulation);
        tvLanguages = findViewById(R.id.tvLanguages);
        tvFlag = findViewById(R.id.tvFlag);
        tvMap = findViewById(R.id.tvMap);
        tvOfficial = findViewById(R.id.tvOfficialName);

        String countryCode = getIntent().getStringExtra("countryCode");

        if (countryCode != null) {
            fetchCountryDetails(countryCode);
        } else {
            Toast.makeText(this, "Không tìm thấy mã quốc gia!", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
    private void fetchCountryDetails(String countryCode) {
        APIService apiService = APIClient.getRetrofitInstance().create(APIService.class);

        // Gọi API
        apiService.getAllCountries().enqueue(new Callback<List<Country>>() {
            @Override
            public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Lọc danh sách quốc gia theo countryCode
                    List<Country> countries = response.body();
                    Country selectedCountry = null;
                    for (Country country : countries) {
                        if (country.getCca3().equalsIgnoreCase(countryCode)) {
                            selectedCountry = country;
                            break;
                        }
                    }

                    if (selectedCountry != null) {
                        displayCountryDetails(selectedCountry);
                    } else {
                        Toast.makeText(CountryDetailActivity.this, "Không tìm thấy quốc gia!", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(CountryDetailActivity.this, "Không lấy được dữ liệu!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Country>> call, Throwable t) {
                Toast.makeText(CountryDetailActivity.this, "Lỗi: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void displayCountryDetails(Country country) {
        // Hiển thị dữ liệu
        tvCountryName.setText(country.getName());
        tvRegion.setText("Vùng đất: " + country.getRegion());
        tvCapital.setText("Thủ đô: " + (country.getCapital() != null ? country.getCapital()[0] : "N/A"));
        tvPopulation.setText("Dân số: " + country.getPopulation());
        tvLanguages.setText("Ngôn ngữ: " + String.join(", ", country.getLanguages().values()));
        tvMap.setText("Bản đồ: " + country.getMap());
        tvOfficial.setText("Tên chính thức: " + country.getOfficial());
        Glide.with(this)
                .load(country.getFlags())      // Dùng URL
                .into(tvFlag);
    }
}