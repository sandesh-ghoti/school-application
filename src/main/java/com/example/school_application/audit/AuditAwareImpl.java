package com.example.school_application.audit;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component("auditAwareImpl")
public class AuditAwareImpl implements AuditorAware<String> {

  @Override
  public @NonNull Optional<String> getCurrentAuditor() {
    return Optional.of("School-Application");
  }
}
