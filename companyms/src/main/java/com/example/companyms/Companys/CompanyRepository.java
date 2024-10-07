package com.example.companyms.Companys;

import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;

@Primary
public interface CompanyRepository extends JpaRepository<Company, Long> {
}
