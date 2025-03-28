package com.example.fqw.repositories;

import com.example.fqw.entity.Calendar;
import com.example.fqw.entity.Master;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    Optional<Calendar> findCalendarByDate(LocalDate date);

    List<Calendar> findCalendarsByMastersId(Long masterId);

    List<Calendar> findAllByMastersInAndDateGreaterThanEqual(List<Master> masters, LocalDate date);

}
