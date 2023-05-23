package com.draizyyy.demo.controller;

import com.draizyyy.demo.model.Day;
import com.draizyyy.demo.model.Lesson;
import com.draizyyy.demo.model.User;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/days")
public class DayController {
    List<Day> days = new ArrayList<>();
    boolean isFilled = false;

    private void initData() {
        final List<Lesson> lessonList = new ArrayList<>();
        Lesson lesson = new Lesson("Литература", "08:45", "09:30", "5", "215", "Пролетарский проспект", "Лицей 1511", "Андреев М.Ф.", "Прочитать \"Бэлу\", подготовить ответы с опорой на текст на вопросы из ДЗ: закладки/выписанные на листок с заданием номера страниц. Учим и сдаём второе из пяти стихотворений Лермонтова. И ещё много много много много много много много много много много много много много много много много много много много много текста");
        Lesson lesson1 = new Lesson("Английский язык", "09:50", "10:35");
        Lesson lesson2 = new Lesson("Информатика", "10:50", "11:35","4", "123", "", "", "Янков В.Ю.");
        Lesson lesson3 = new Lesson("Алгебра", "11:45", "12:30");
        Lesson lesson4 = new Lesson("Алгебра", "12:45", "13:30", "5", "21А", "Кленовый Бульвар", "Лицей 1523", "Алексеева Н.И.", "А-9, &22, № 22.6, 22.8, 22.14, 22.16, 22.20");
        Lesson lesson5 = new Lesson("Химия", "13:45", "14:30", "4", "", "", "", "", "Знать определения.");
        Lesson lesson6 = new Lesson("Обществознание", "14:35", "15:35", "3","37", "Аоаоаоа", "Лицей 1123", "Никитин А.П.", "Прочитать §16, ответить на вопросы на стр. 64-65.");
        Lesson lesson7 = new Lesson("Информатика", "15:45", "16:30", "5", "52", "", "Лицей 1523");
        Lesson lesson8 = new Lesson("Основы безопаснос...", "16:45", "17:30");

        lessonList.add(lesson);
        lessonList.add(lesson1);
        lessonList.add(lesson2);
        lessonList.add(lesson3);
        lessonList.add(lesson4);
        lessonList.add(lesson5);
        lessonList.add(lesson6);
        lessonList.add(lesson7);
        lessonList.add(lesson8);

        Day day0 = new Day("Воскресенье", new ArrayList<>(), "12 марта");
        Day day1 = new Day("Понедельник", lessonList, "13 марта");
        Day day2 = new Day("Вторник", lessonList, "14 марта");
        Day day3 = new Day("Среда", lessonList, "15 марта");
        Day day4 = new Day("Четверг", lessonList, "16 марта");
        Day day5 = new Day("Пятница", lessonList, "17 марта");
        Day day6 = new Day("Суббота", lessonList, "18 марта");
        Day day7 = new Day("Воскресенье", lessonList, "19 марта");
        Day day8 = new Day("Понедельник", lessonList, "20 марта");

        days.add(day0);
        days.add(day1);
        days.add(day2);
        days.add(day3);
        days.add(day4);
        days.add(day5);
        days.add(day6);
        days.add(day7);
        days.add(day8);
    }

    @GetMapping
    public List<Day> getAllDays() {
        if (!isFilled) {
            initData();
            isFilled = !isFilled;
        }
        return days;
    }

    @PostMapping
    public ResponseEntity<List<Day>> addDay(@RequestBody Day newDay) {
        for (Day day: days) {
            if (Objects.equals(day.getId(), newDay.getId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }
        }
        days.add(newDay);
        return ResponseEntity.ok(days);
    }
    @PutMapping
    public ResponseEntity<Day> updateDay(@RequestBody Day updatedDay) {
        int counter = 0;
        for (Day day: days) {
            if (Objects.equals(day.getId(), updatedDay.getId())) {
                days.set(counter, updatedDay);
                return ResponseEntity.ok(days.get(counter));
            }
            counter++;
        } return ResponseEntity.notFound().build();
    }

    @GetMapping("/someone")
    public Day getSomeone() {
        final List<Lesson> lessonList = new ArrayList<>();
        Lesson lesson = new Lesson("Литература", "08:45", "09:30", "5", "215", "Пролетарский проспект", "Лицей 1511", "Андреев М.Ф.", "Прочитать \"Бэлу\", подготовить ответы с опорой на текст на вопросы из ДЗ: закладки/выписанные на листок с заданием номера страниц. Учим и сдаём второе из пяти стихотворений Лермонтова. И ещё много много много много много много много много много много много много много много много много много много много много текста");
        Lesson lesson1 = new Lesson("Английский язык", "09:50", "10:35");
        Lesson lesson2 = new Lesson("Информатика", "10:50", "11:35","4", "123", "", "", "Янков В.Ю.");
        Lesson lesson3 = new Lesson("Алгебра", "11:45", "12:30");
        Lesson lesson4 = new Lesson("Алгебра", "12:45", "13:30", "5", "21А", "Кленовый Бульвар", "Лицей 1523", "Алексеева Н.И.", "А-9, &22, № 22.6, 22.8, 22.14, 22.16, 22.20");
        Lesson lesson5 = new Lesson("Химия", "13:45", "14:30", "4", "", "", "", "", "Знать определения.");
        Lesson lesson6 = new Lesson("Обществознание", "14:35", "15:35", "3","37", "Аоаоаоа", "Лицей 1123", "Никитин А.П.", "Прочитать §16, ответить на вопросы на стр. 64-65.");
        Lesson lesson7 = new Lesson("Информатика", "15:45", "16:30", "5", "52", "", "Лицей 1523");
        Lesson lesson8 = new Lesson("Основы безопаснос...", "16:45", "17:30");

        lessonList.add(lesson);
        lessonList.add(lesson1);
        lessonList.add(lesson2);
        lessonList.add(lesson3);
        lessonList.add(lesson4);
        lessonList.add(lesson5);
        lessonList.add(lesson6);
        lessonList.add(lesson7);
        lessonList.add(lesson8);

        Day day = new Day("Понедельник", "22 мая");

        for (Lesson lesson_: lessonList) {
            lesson_.dayId = day.getId();
        }

        day.LessonsList = lessonList;

        return day;
    }
}
