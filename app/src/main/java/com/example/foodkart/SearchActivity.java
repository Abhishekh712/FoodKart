package com.example.foodkart;

import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class SearchActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {

    private static final int SPEECH_REQUEST_CODE = 101;
    private EditText searchEditText;
    private RecyclerView recyclerView;
    private HomeAdapter adapter;
    private TextView emptyStateText;
    private TextToSpeech tts;
    private List<Restaurant> allRestaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        allRestaurants = MockData.getRestaurants();
        tts = new TextToSpeech(this, this);

        initUI();
    }

    private void initUI() {
        searchEditText = findViewById(R.id.searchEditText);
        recyclerView = findViewById(R.id.searchRecyclerView);
        emptyStateText = findViewById(R.id.emptyStateText);
        
        adapter = new HomeAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
        
        findViewById(R.id.btnVoiceSearch).setOnClickListener(v -> startVoiceRecognition());

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filter(s.toString());
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    private void filter(String query) {
        if (query.isEmpty()) {
            adapter.submitList(new ArrayList<>());
            emptyStateText.setVisibility(View.VISIBLE);
            return;
        }

        List<HomeItem> filteredItems = new ArrayList<>();
        String lowerQuery = query.toLowerCase();

        for (Restaurant r : allRestaurants) {
            boolean matches = r.getName().toLowerCase().contains(lowerQuery) || 
                              r.getCuisine().toLowerCase().contains(lowerQuery);
            
            if (!matches) {
                for (FoodItem item : r.getMenu()) {
                    if (item.getName().toLowerCase().contains(lowerQuery)) {
                        matches = true;
                        break;
                    }
                }
            }

            if (matches) {
                filteredItems.add(new HomeItem(r));
            }
        }

        adapter.submitList(filteredItems);
        emptyStateText.setVisibility(filteredItems.isEmpty() ? View.VISIBLE : View.GONE);
        if (filteredItems.isEmpty()) {
            emptyStateText.setText("No results found for \"" + query + "\"");
        }
    }

    private void startVoiceRecognition() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak now...");
        try {
            startActivityForResult(intent, SPEECH_REQUEST_CODE);
        } catch (Exception e) {
            Toast.makeText(this, "Speech recognition not supported on this device", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SPEECH_REQUEST_CODE && resultCode == RESULT_OK && data != null) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            if (result != null && !result.isEmpty()) {
                String voiceQuery = result.get(0);
                searchEditText.setText(voiceQuery);
                speak("Searching for " + voiceQuery);
            }
        }
    }

    private void speak(String text) {
        if (tts != null) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {
            tts.setLanguage(Locale.US);
        }
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}
