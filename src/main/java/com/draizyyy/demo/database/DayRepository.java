package com.draizyyy.demo.database;

import com.draizyyy.demo.model.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DayRepository extends JpaRepository<Day, Long> {
    List<Day> findAll();
    Optional<Day> findById(Long id);
    Day save(Day day);
}
