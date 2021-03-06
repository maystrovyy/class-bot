package com.maystrovyy.repositories;

import com.maystrovyy.models.Period;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeriodRepository extends JpaRepository<Period, Long> {

    List<Period> findByGroupName(String groupName);

    List<Period> findByGroupNameAndLessonWeek(String groupName, Integer lessonWeek);

    List<Period> findByGroupNameAndDayNumberAndLessonWeek(String groupName, int dayNumber, Integer lessonWeek);

}