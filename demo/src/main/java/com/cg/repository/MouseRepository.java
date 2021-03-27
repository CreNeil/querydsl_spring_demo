package com.cg.repository;

import com.cg.domain.Mouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MouseRepository extends JpaRepository<Mouse, String> {
}
