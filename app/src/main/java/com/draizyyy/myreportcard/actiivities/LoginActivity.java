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
import com.draizyyy.myreportcard.databinding.ActivityLoginBinding;
import com.draizyyy.myreportcard.pojos.Day;
import com.draizyyy.myreportcard.pojos.News;
import com.draizyyy.myreportcard.retrofit.NetworkService;
import com.draizyyy.myreportcard.room.DayDao;
import com.draizyyy.myreportcard.room.NewsDao;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    NetworkService networkService;
    private ActivityLoginBinding binding;
    Vibrator v;
    Handler h;
    private boolean isMainActivityLoaded = false;
    private boolean isTaskSuccessful = false;
    private boolean isAnimationEnded = false;
    Animation close;
    Animation open;
    Animation fadeOut;
    Animation fadeIn;
    Animation close_reverse;
    Animation open_reverse;
    Animation fadeOut_reverse;
    Animation fadeIn_reverse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        networkService = new NetworkService();
        networkService.sendMessage("login activity created");
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        FirebaseAuth auth = FirebaseAuth.getInstance();
        setContentView(binding.getRoot());

        h = new Handler();

        close = AnimationUtils.loadAnimation(this, R.anim.close_schoolnet_logo);
        open = AnimationUtils.loadAnimation(this, R.anim.open_preloader_anim);
        fadeOut = AnimationUtils.loadAnimation(this, R.anim.fade_out);
        fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in);
        close_reverse = AnimationUtils.loadAnimation(this, R.anim.close_schoolnet_logo_reverse);
        open_reverse = AnimationUtils.loadAnimation(this, R.anim.open_preloader_anim_reverse);
        fadeOut_reverse = AnimationUtils.loadAnimation(this, R.anim.fade_out_reverse);
        fadeIn_reverse = AnimationUtils.loadAnimation(this, R.anim.fade_in_reverse);

        if (auth.getCurrentUser() != null) {
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            intent.putExtra("email", auth.getCurrentUser().getEmail());
//            intent.putExtra("name", getTextValue(binding.email));
            startActivity(intent);
            finish();
//            binding.registrationButton.setOnClickListener(view -> {
//                Intent intent1 = new Intent();
//            });
        }
        binding.registrationButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegistrationActivity.class);
//            intent.putExtra("name", getTextValue(binding.email));
            startActivity(intent);
        });
        close.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }
            @Override
            public void onAnimationEnd(Animation animation) {
                binding.loadingAnim.setImageResource(R.drawable.load_animation);
                binding.schoolnetButItsText.setText("Загрузка...");
                binding.loadingAnim.startAnimation(open);
                binding.schoolnetButItsText.startAnimation(fadeIn);
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        open.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                new Thread(() -> {
//                    try {
//                        Thread.sleep(10000);
//                    } catch (InterruptedException e) {
//                        throw new RuntimeException(e);
//                    }
//
//                    }
//                }).start();
                AsyncTask.execute(() -> {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    isAnimationEnded = true;
                    startMainActivity(binding.loginLogin.getText().toString().trim());
                    isTaskSuccessful = false;
                    isAnimationEnded = false;
                });
            }
            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        binding.loginButton.setOnClickListener(view1 -> {
            v.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE));
            binding.schoolnetButItsText.startAnimation(fadeOut);
            binding.loadingAnim.startAnimation(close);
            new Thread(() -> {
                String email = binding.loginLogin.getText().toString().trim();
                String password = binding.loginPassword.getText().toString().trim();
                Log.i("MY APP", "email: " + email + ", password " + password);
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        isTaskSuccessful = true;
                        Log.i("MY APP", "Server Ss Accessible");
                        if (isAnimationEnded) {
                            isMainActivityLoaded = true;
                            startMainActivity(email);
                            isTaskSuccessful = false;
                            isMainActivityLoaded = false;
                        }
                    }
                });
            }).start();
        });
    }

    private void makeBadToast() {
        Toast.makeText(getApplicationContext(), "Сервер недоступен", Toast.LENGTH_LONG).show();
    }
    private void startMainActivity(String email) {
        new Thread(() -> {
            if (networkService.isServerAccessible()) {
                networkService.sendMessage("App created");
                List<News> news = networkService.getNews();
                networkService.sendMessage("News initialized");
                List<Day> days = networkService.getDays();
                networkService.sendMessage("Days initialized");
                NewsDao newsDao = App.getDatabase().newsDao();
                networkService.sendMessage("NewsDao initialized");
                DayDao dayDao = App.getDatabase().dayDao();
                networkService.sendMessage("dayDao initialized");
                //            newsList.add(new News("Администрация", "20 апреля", "Завтра уроки отменяются"));
                //            newsList.add(new News("Королёв Б.И.", "17 апреля", "Завтра отменяются все уроки физики, потому что я сегодня добрый."));
                //            newsList.add(new News("Кручинина О.Б.", "19 апреля", "Сегодня уроков не будет, можете идти домой"));
                if (news.size() != 0 && days.size() != 0) {
                    networkService.sendMessage("news.size() != 0 && days.size() != 0");
                    dayDao.deleteAllLessons();
                    dayDao.deleteAllDays();
                    newsDao.deleteAllNews();
                    newsDao.insertAllNews(news);
                    dayDao.insertAllDays(days);
                    Intent intent2 = new Intent(LoginActivity.this, MainActivity.class);
                    intent2.putExtra("email", email);
                    //            intent.putExtra("name", getTextValue(binding.email));
                    startActivity(intent2);
                    finish();
                }
            } else {
                h.post(() -> makeBadToast());
                binding.loadingAnim.startAnimation(open_reverse);
                binding.schoolnetButItsText.startAnimation(fadeIn_reverse);
                fadeIn_reverse.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        String name = "SchoolNet";
                        binding.loadingAnim.setImageResource(R.drawable.iconus_v2_gif);
                        binding.schoolnetButItsText.setText(name);
                        binding.loadingAnim.startAnimation(fadeOut_reverse);
                        binding.schoolnetButItsText.startAnimation(close_reverse);
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {
                    }
                });
            }
        }).start();
    }
}