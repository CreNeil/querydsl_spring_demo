package com.cg.repository;

import com.cg.domain.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonitorRepository extends JpaRepository<Monitor, Long> {
}
