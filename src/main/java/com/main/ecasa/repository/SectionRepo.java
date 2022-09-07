package com.main.ecasa.repository;

import com.main.ecasa.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SectionRepo  extends JpaRepository<Section,Long> {




}
