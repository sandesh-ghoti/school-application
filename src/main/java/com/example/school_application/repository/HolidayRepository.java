package com.example.school_application.repository;

import com.example.school_application.model.Holiday;
import com.example.school_application.utils.Constants.Type;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends JpaRepository<Holiday, Long> {
  public List<Holiday> findByType(Type type);
}
