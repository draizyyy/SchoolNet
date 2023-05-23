package com.draizyyy.demo.controller;

import com.draizyyy.demo.model.News;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/news")
public class NewsContoller {
    List<News> newsList = new ArrayList<>();
    boolean isFilled = false;

    private void initData() {
        newsList.add(new News("Администрация", "20 апреля", "Завтра уроки отменяются"));
        newsList.add(new News("Королёв Б.И.", "17 апреля", "Завтра отменяются все уроки физики, потому что я сегодня добрый."));
        newsList.add(new News("Кручинина О.Б.", "19 апреля", "Сегодня уроков не будет, можете идти домой"));
    }

    @GetMapping
    public List<News> getAllNews() {
        if (!isFilled) {
            initData();
            isFilled = !isFilled;
        }
        return newsList;
    }

    @PostMapping
    public ResponseEntity<List<News>> addNews(@RequestBody News newNews) {
        for (News news: newsList) {
            if (Objects.equals(news.getId(), newNews.getId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        newsList.add(newNews);
        return ResponseEntity.ok(newsList);
    }
    @PutMapping
    public ResponseEntity<News> updateNews(@RequestBody News updatedNews) {
        int counter = 0;
        for (News news: newsList) {
            if (Objects.equals(news.getId(), updatedNews.getId())) {
                newsList.set(counter, updatedNews);
                return ResponseEntity.ok(newsList.get(counter));
            }
            counter++;
        } return ResponseEntity.notFound().build();
    }
}