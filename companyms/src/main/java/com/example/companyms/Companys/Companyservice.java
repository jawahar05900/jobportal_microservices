package com.example.companyms.Companys;

import java.util.List;

public interface Companyservice {
    List<Company> getallcompanies();
    boolean updatecompany(Company company,Long id);
    void createcompany(Company company);
    boolean deletecompanybyid(Long id);
    Company getcompanybyid(Long id);


}
