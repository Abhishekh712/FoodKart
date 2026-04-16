package com.example.foodkart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RecommendationViewModel extends ViewModel {
    private final MutableLiveData<List<Restaurant>> rankedRestaurants = new MutableLiveData<>();
    private final MutableLiveData<AHPResult> currentWeights = new MutableLiveData<>();
    
    private final NormalizationEngine normalizationEngine = new NormalizationEngine();
    private final TopsisEngine topsisEngine = new TopsisEngine();
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    private List<Restaurant> allRestaurants = new ArrayList<>();
    private DecisionState decisionState;

    public LiveData<List<Restaurant>> getRankedRestaurants() {
        return rankedRestaurants;
    }

    public LiveData<AHPResult> getCurrentWeights() {
        return currentWeights;
    }

    public void initData(List<Restaurant> restaurants, double userBudget) {
        this.allRestaurants = restaurants;
        this.decisionState = new DecisionState(restaurants);
        
        executor.execute(() -> {
            for (Restaurant r : allRestaurants) {
                FuzzyNumber[] vector = normalizationEngine.generateFuzzyVector(r, userBudget);
                decisionState.cacheVector(r.getId(), vector);
            }
            // Initial default ranking
            updateRanking(50, 50, 50);
        });
    }

    public void updateRanking(double budgetPref, double qualityPref, double proximityPref) {
        executor.execute(() -> {
            AHPResult ahp = AHPEngine.calculateAHP(new double[]{budgetPref, qualityPref, proximityPref});
            List<Restaurant> ranked = topsisEngine.rankRestaurants(decisionState, ahp.weights);
            
            currentWeights.postValue(ahp);
            rankedRestaurants.postValue(ranked);
        });
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        normalizationEngine.shutdown();
        topsisEngine.shutdown();
        executor.shutdown();
    }
}