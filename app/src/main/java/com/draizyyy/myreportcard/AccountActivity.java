package com.draizyyy.myreportcard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.draizyyy.myreportcard.databinding.ActivityAccountBinding;
import com.google.firebase.auth.FirebaseAuth;

public class AccountActivity extends Fragment {
    private ActivityAccountBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = binding.inflate(inflater, container, false);
        binding.exitButton.setOnClickListener(view -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(AccountActivity.this.getActivity(), LoginActivity.class);
//            intent.putExtra("name", getTextValue(binding.email));
            startActivity(intent);
            requireActivity().finish();
        });
        return binding.getRoot();
    }
}