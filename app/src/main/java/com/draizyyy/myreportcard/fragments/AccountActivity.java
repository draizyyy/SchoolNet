package com.draizyyy.myreportcard.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.draizyyy.myreportcard.App;
import com.draizyyy.myreportcard.retrofit.NetworkService;
import com.draizyyy.myreportcard.actiivities.LoginActivity;
import com.draizyyy.myreportcard.pojos.User;
import com.draizyyy.myreportcard.databinding.ActivityAccountBinding;
import com.draizyyy.myreportcard.room.DayDao;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class AccountActivity extends Fragment {
    private ActivityAccountBinding binding;
    private String nameAndSurname;
    private String mail;
    private boolean isCreatingEnded = false;
    private Handler h;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(inflater, container, false);

        h = new Handler();
        setupUI();

        isCreatingEnded = true;
        return binding.getRoot();
    }
    private void updateUI() {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .detach(this)
                .attach(this)
                .commit();
    }
    private void setupUI() {
        binding.nameAndSurname.setText(nameAndSurname);
        binding.userMail.setText(mail);
        binding.exitButton.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(AccountActivity.this.getActivity(), LoginActivity.class);
//            intent.putExtra("name", getTextValue(binding.email));
            startActivity(intent);
            requireActivity().finish();
        });
    }
    public void getNameAndSurname(String email) {
        new Thread(() -> {
            NetworkService networkService = new NetworkService();
            DayDao dayDao = App.getDatabase().dayDao();
            User user;
            if (networkService.isServerAccessible()) {
                Log.d("MY APP", "AccountFragment: server is accessible");
                user = networkService.getUserByMail(email);
            }
            else {
                user = dayDao.getUserByEmail(email);
                Log.v("MY APP", "user by email: user.email " + user.mail + " user.name " + user.name + "user.surname" + user.surname);
            }
            nameAndSurname = user.name + " " + user.surname;
            mail = user.mail;
            if (isCreatingEnded) {
                h.post(() -> setupUI());
                h.post(() -> updateUI());
                isCreatingEnded = false;
            }
        }).start();
    }

}