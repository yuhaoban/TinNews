package com.laioffer.tinnews.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.model.NewsResponse;
import com.laioffer.tinnews.repository.NewsRepository;
import com.laioffer.tinnews.repository.NewsViewModelFactory;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        NewsRepository repository = new NewsRepository();
//        viewModel = new HomeViewModel(repository);
        viewModel = new ViewModelProvider(this, new NewsViewModelFactory(repository)).get(HomeViewModel.class);
        viewModel.setCountryInput("us");
        viewModel.getTopHeadlines().observe(
                getViewLifecycleOwner(),
                new Observer<NewsResponse>() {
                    @Override
                    public void onChanged(NewsResponse newsResponse) {
                        if (newsResponse != null) {
                            Log.d("HomeFragment", newsResponse.toString());
                        }
                    }
                });
    }
}