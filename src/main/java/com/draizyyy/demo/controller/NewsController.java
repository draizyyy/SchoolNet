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
public class NewsController {
    List<News> newsList = new ArrayList<>();
    boolean isFilled = false;

    private void initData() {
        newsList.add(new News("Администрация", "20 марта", "На следующей неделе будут каникулы."));
        newsList.add(new News("Кручинина О.Б.", "19 марта", "Найден учебник по химии. Потерявшему нужно обратиться ко мне."));
        newsList.add(new News("Королёв Б.И.", "17 марта", "Завтра отменяются все уроки физики."));
        newsList.add(new News("Администрация", "15 марта", "Сегоя у 10А отменяется пара географии."));
        newsList.add(new News("Администрация", "14 марта", "У 9М и 9Н завтра отменяются пары математики."));
        newsList.add(new News("Королёв Б.И.", "13 марта", "Не забудьте подойти ко мне после уроков."));
        newsList.add(new News("Администрация", "12 марта", "Завтра в 19:00 будет спектакль. Просьба не опаздывать."));
        newsList.add(new News("Администрация", "10 марта", "Сегодня отменяются пары английского языка у 8 классов"));
        newsList.add(new News("Алексеева Н.И.", "8 марта", "Завтра на уроке геометрии потребуется макет."));
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