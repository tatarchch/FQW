package com.example.fqw.repositories;

import com.example.fqw.entity.Master;
import com.example.fqw.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findAllByMasterAndDate(Master master, LocalDate date);

    List<Record> findAllByDateAndTiming(LocalDate date, String timing);

}
