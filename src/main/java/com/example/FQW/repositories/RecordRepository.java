package com.example.FQW.repositories;

import com.example.FQW.entity.Master;
import com.example.FQW.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {

    List<Record> findAllByMasterAndDate(Master master, LocalDate date);

}
