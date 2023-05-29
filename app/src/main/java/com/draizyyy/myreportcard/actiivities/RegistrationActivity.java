package com.draizyyy.myreportcard.actiivities;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.draizyyy.myreportcard.App;
import com.draizyyy.myreportcard.R;
import com.draizyyy.myreportcard.pojos.User;
import com.draizyyy.myreportcard.databinding.FragmentRegistrationBinding;
import com.draizyyy.myreportcard.retrofit.NetworkService;
import com.draizyyy.myreportcard.room.DayDao;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {
    private FragmentRegistrationBinding binding;
    final Handler h = new Handler();
    Vibrator v;
    Animation close;
    Animation open;
    Animation fadeOut;
    Animation fadeIn;
    Animation close_reverse;
    Animation open_reverse;
    Animation fadeOut_reverse;
    Animation fadeIn_reverse;
    private void makeBadToast() {
        Toast.makeText(getApplicationContext(), "Сервер недоступен", Toast.LENGTH_LONG).show();
    }
    private void makeMyToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        close = AnimationUtils.loadAnimation(this, R.anim.close_schoolnet_logo);
        open = AnimationUtils.loadAnimation(this, R.anim.open_preloader_anim);
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        close_reverse = AnimationUtils.loadAnimation(this, R.anim.close_schoolnet_logo_reverse);
        open_reverse = AnimationUtils.loadAnimation(this, R.anim.open_preloader_anim_reverse);
        fadeOut_reverse = AnimationUtils.loadAnimation(this, R.anim.fade_out_reverse);
        fadeIn_reverse = AnimationUtils.loadAnimation(this, R.anim.fade_in_reverse);

        close.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                binding.loadingAnimReg.setImageResource(R.drawable.load_animation);
                binding.schoolnetName.setText("Загрузка...");
                binding.loadingAnimReg.startAnimation(open);
                binding.schoolnetName.startAnimation(fadeIn);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });

        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        binding = FragmentRegistrationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.regButton.setOnClickListener(view -> {
            binding.schoolnetName.startAnimation(fadeOut);
            binding.loadingAnimReg.startAnimation(close);
            v.vibrate(VibrationEffect.createOneShot(20, VibrationEffect.DEFAULT_AMPLITUDE));
            String login = binding.regLogin.getText().toString().trim();
            String password = binding.regPassword.getText().toString().trim();
            String name = binding.regName.getText().toString().trim();
            String surname = binding.regSurname.getText().toString().trim();
            Log.i("MY APP", "login: " + login + " password " + password);
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(login, password).addOnCompleteListener(task -> {
                if (task.isSuccessful()) {
                    new Thread(() -> {
                        DayDao dayDao = App.getDatabase().dayDao();
                        NetworkService networkService = new NetworkService();
                        System.out.println("login + " + login);
                        User user = new User(login, name, surname);
                        Log.d("MY APP", "isAccessible: " + networkService.isServerAccessible());
                        if (networkService.addUser(user)) {
                            Log.d("MY APP", "addUser: true");
                        }
                        else {
                            Log.d("MY APP", "addUser: false");
                        }


                        if (networkService.isServerAccessible()){
                            networkService.addUser(user);
                            dayDao.deleteAllUsers();
                            dayDao.insertUser(user);
                            Log.i("MY APP", "login " + login + " name " + name + " surname " + surname);
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else {
                            try {
                                FirebaseAuth.getInstance().getCurrentUser().delete();
                            }
                            catch (NullPointerException exception) {
                                exception.printStackTrace();
                            }
                            h.post(() -> makeBadToast());
                        }
                        binding.loadingAnimReg.startAnimation(open_reverse);
                        binding.schoolnetName.startAnimation(fadeIn_reverse);
                        fadeIn_reverse.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }
                            @Override
                            public void onAnimationEnd(Animation animation) {
                                String name = "SchoolNet";
                                binding.loadingAnimReg.setImageResource(R.drawable.iconus_v2_gif);
                                binding.schoolnetName.setText(name);
                                binding.loadingAnimReg.startAnimation(close_reverse);
                                binding.schoolnetName.startAnimation(fadeOut_reverse);
                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {
                            }
                        });
//                        DayDao dayDao = App.getDatabase().dayDao();
//                        dayDao.insertUser(new User(login, name, surname));
                    }).start();
                }
                else {
                    h.post(() -> makeMyToast("Проверьте правильность введённых данных"));
                    binding.loadingAnimReg.setAnimation(open_reverse);
                    binding.schoolnetName.setAnimation(fadeIn_reverse);
                    open_reverse.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {

                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            String name = "SchoolNet";
                            binding.loadingAnimReg.setImageResource(R.drawable.iconus_v2_gif);
                            binding.schoolnetName.setText(name);
                            binding.loadingAnimReg.startAnimation(close_reverse);
                            binding.schoolnetName.startAnimation(fadeOut_reverse);
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                }
            });
        });
    }
}