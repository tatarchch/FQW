package com.example.FQW.repositories;

import com.example.FQW.entity.Calendar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Repository
public interface CalendarRepository extends JpaRepository<Calendar, Long> {

    Optional<Calendar> findCalendarByDate(Date date);

    /*@Query("select calendar.id, calendar.date" +
            "from Calendar calendar" +
            "join Calendar masters" +
            "join Master calendars" +
            "where master.id = :masterId and master.active = true")
    List<Calendar> queryAllCalendarsByMasterId(Long id);*/

}
