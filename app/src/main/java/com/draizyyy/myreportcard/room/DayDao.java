package com.draizyyy.myreportcard.room;

import android.util.Log;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Transaction;

import com.draizyyy.myreportcard.pojos.Day;
import com.draizyyy.myreportcard.pojos.Lesson;
import com.draizyyy.myreportcard.pojos.User;

import java.util.ArrayList;
import java.util.List;

@Dao
public abstract class DayDao {
//    public void insertAll(List<User> users) {
//        for(User user:users) {
//            if(user.pets != null) {
//                insertPetsForUser(user, user.pets);
//            }
//        }
//        _insertAll(users);
//    }
//
//    private void insertPetsForUser(User user, List<Pet> pets){
//
//        for(Pet pet : pets){
//            pet.setUserId(user.getId());
//        }
//
//        _insertAll(pets);
//    }
//
//    public List<User> getUsersWithPetsEagerlyLoaded() {
//        List<UserWithPets> usersWithPets = _loadUsersWithPets();
//        List<User> users = new ArrayList<User>(usersWithPets.size())
//        for(UserWithPets userWithPets: usersWithPets) {
//            userWithPets.user.pets = userWithPets.pets;
//            users.add(userWithPets.user);
//        }
//        return users;
//    }
//
//
//    //package private methods so that wrapper methods are used, Room allows for this, but not private methods, hence the underscores to put people off using them :)
//    @Insert
//    abstract void _insertAll(List<Pet> pets);
//
//    @Insert
//    abstract void _insertAll(List<User> users);
//
//    @Query("SELECT * FROM User")
//    abstract List<UserWithPets> _loadUsersWithPets();
//    List<DayWithLessons> getALl();
//    @Query("SELECT * FROM Day where id = :id")
//    Day getById(int id);
//    @Delete
//    void delete(DayWithLessons dayWithLessons);
//    @Insert
//    void save(DayWithLessons dayWithLessons) {
//        for ()
//    }
//    @Insert
//    void saveAll(List<DayWithLessons> dayWithLessonsList);
//    @Update
//    void update(DayWithLessons dayWithLessons);
//
//    public void insertAll(List<Day> days) {
//        for(Day day: days) {
//            if(day.LessonsList != null) {
//                insertLessonsForDay(day);
//            }
//        }
//        insertAll(days);
//    }
//
//    private void insertLessonsForDay(Day day){
//        List<Lesson> lessons = day.LessonsList;
//
//        for(Lesson lesson : lessons){
//            lesson.id = day.id;
//        }
//
//        insertAllLessons(lessons);
//    }
//
//    public List<Day> getDayWithLessonsLoaded() {
//        List<DayWithLessons> daysWithLessons = loadDayWithLessons();
//        List<Day> days = new ArrayList<Day>(daysWithLessons.size());
//        for(DayWithLessons dayWithLessons: daysWithLessons) {
//            dayWithLessons.day.LessonsList = dayWithLessons.lessons;
//            days.add(dayWithLessons.day);
//        }
//        return days;
//    }
//    @Insert
//    abstract void insertAllLessons(List<Lesson> lessons);
//
//    @Insert
//    abstract void insertAllDays(List<Day> users);
//
//    @Query("SELECT * FROM Day")
//    abstract List<DayWithLessons> loadDayWithLessons();

//    @Insert
//    void insertLessonList(List<Lesson> lessons);


    //AAAAAAAAAAAA
    @Query("SELECT DISTINCT `Lesson name` FROM Lesson")
    public abstract List<String> getAllLessonNames();
    @Query("SELECT grade FROM Lesson WHERE `Lesson name` = :name")
    public abstract List<String> getAllGradesForLesson(String name);
    @Query("SELECT * FROM Day")
    public abstract List<Day> loadAllDays();

    @Query("SELECT * FROM Lesson")
    public abstract List<Lesson> loadAllLessons();

    @Query("SELECT * FROM Lesson WHERE dayId = :dayId")
    public abstract List<Lesson> loadAllLessonsById(int dayId);
    @Transaction
    public List<Day> getAllDayWithNotNullLessonsHomework() {
//        Log.i("MY APP", "getAllDayWithNotNullLessonsHomework(): done");
//        List<Lesson> test = loadAllLessons();
//        Log.i("MY APP", "loadAllLessons size: " + test.size());
//        for (Lesson lesson: test) {
//            System.out.println(lesson.name + " id: " + lesson.id + " dayId: " + lesson.dayId);
//            if (lesson.homework == null || lesson.homework.equals("") || lesson.homework.equals(" ")) {
//                test.remove(lesson);
//                Log.i("MY APP", "deleted");
//            }
        List<Day> days =  loadAllDays();
        List<Lesson> deleteLessons = new ArrayList<>();
        List<Day> deleteDays = new ArrayList<>();
        Log.i("MY APP", "loaded all days");
        Log.i("MY APP", "day.size: " + (days.size()));
        for (Day day: days) {
            day.LessonsList = loadAllLessonsById(day.id);
            for (Lesson lesson: day.LessonsList) {
                if (lesson.homework == null || lesson.homework.equals("") || lesson.homework.equals(" ")) {
                    deleteLessons.add(lesson);
                }
            }
            for (Lesson lesson: deleteLessons) {
                day.LessonsList.remove(lesson);
            }
            if (day.LessonsList.size() == 0) {
                Log.i("MY APP", "day " + day.day_name + "deleted");
                deleteDays.add(day);
            }
        }
        for (Day day: deleteDays) {
            days.remove(day);
        }
        Log.i("MY APP", "loaded all lessons by id");
        return days;
    }
    @Transaction
    public List<Day> getAll() {
        Log.i("MY APP", "done");
//        List<Lesson> test = loadAllLessons();
//        Log.i("MY APP", "loadAllLessons size: " + test.size());
//        for (Lesson lesson: test) {
//            System.out.println(lesson.name + " id: " + lesson.id + " dayId: " + lesson.dayId);
//        }
        List<Day> days = loadAllDays();
        Log.i("MY APP", "loaded all days");
        Log.i("MY APP", "day.size: " + (days.size()));
        for (Day day: days) {
            day.LessonsList = loadAllLessonsById(day.id);
            Log.i("MY APP", "lessonlist dao size:" + loadAllLessonsById(day.id).size());
        }
        Log.i("MY APP", "loaded all lessons by id");
        return days;
    }

//    @Transaction
//    public void insertAllLessons(List<Lesson> lessons) {
//        for (Lesson lesson: lessons) {
//            insertLesson(lesson);
//        }
//    }
    @Transaction
    public void insertAllDays(List<Day> days) {
        Log.i("MY APP", "inserting days...");
        Log.i("MY APP", "dl.size: " + days.size());
        for (Day day: days) {
            insertDay(day);
            int id = selectLastDayId();
            Log.i("MY APP", "dayId: " + day.id);
            for (Lesson lesson: day.LessonsList) {
                Log.i("MY APP", lesson.name);
                lesson.dayId = id;
                System.out.println("загрузка в бд: " + lesson.name + " dayId: " + lesson.dayId);
                insertLesson(lesson);
            }
        }
    }
    @Insert
    public abstract void insertLesson(Lesson lesson);
    @Insert
    public abstract void insertDay(Day day);

    @Query("SELECT id FROM Day WHERE id = (SELECT MAX(id) FROM Day)")
    public abstract int selectLastDayId();

    @Query("DELETE FROM Lesson")
    public abstract void deleteAllLessons();

    @Query("DELETE FROM Day")
    public abstract void deleteAllDays();
    @Insert
    public abstract void insertUser(User user);
    @Query("SELECT * FROM User WHERE mail = :mail")
    public abstract User getUserByEmail(String mail);

    @Query("DELETE FROM User")
    public abstract void deleteAllUsers();
}
