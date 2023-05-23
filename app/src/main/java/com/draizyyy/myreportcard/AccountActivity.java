package com.draizyyy.myreportcard;

import android.content.Intent;
import android.icu.util.Freezable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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
    public void getNameAndSurname() {
        new Thread(() -> {
            String email = Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getEmail();
            DayDao dayDao = App.getDatabase().dayDao();
            User user = dayDao.getUserByEmail(email);
            nameAndSurname = user.name + " " + user.surname;
            mail = email;
        }).start();
    }
}