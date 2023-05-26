package com.draizyyy.myreportcard.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.draizyyy.myreportcard.retrofit.NetworkService;
import com.draizyyy.myreportcard.actiivities.LoginActivity;
import com.draizyyy.myreportcard.pojos.User;
import com.draizyyy.myreportcard.databinding.ActivityAccountBinding;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class AccountActivity extends Fragment {
    private ActivityAccountBinding binding;
    private String nameAndSurname;
    private String mail;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAccountBinding.inflate(inflater, container, false);
        binding.nameAndSurname.setText(nameAndSurname);
        binding.userMail.setText(mail);
        binding.exitButton.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(AccountActivity.this.getActivity(), LoginActivity.class);
//            intent.putExtra("name", getTextValue(binding.email));
            startActivity(intent);
            requireActivity().finish();
        });
        return binding.getRoot();
    }
    public void getNameAndSurname(String email) {
        new Thread(() -> {
            NetworkService networkService = new NetworkService();
//            DayDao dayDao = App.getDatabase().dayDao();
            User user = networkService.getUserByMail(email);
            nameAndSurname = user.name + " " + user.surname;
            mail = user.mail;
        }).start();
    }

}