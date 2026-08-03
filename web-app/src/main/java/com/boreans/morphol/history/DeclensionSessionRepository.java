package com.boreans.morphol.history;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DeclensionSessionRepository extends JpaRepository<DeclensionSession, Long> {
	java.util.List<DeclensionSession> findAllByOrderByIdDesc();
	java.util.List<DeclensionSession> findAllByBrowserSessionIdOrderByIdDesc(String browserSessionId);
	
}