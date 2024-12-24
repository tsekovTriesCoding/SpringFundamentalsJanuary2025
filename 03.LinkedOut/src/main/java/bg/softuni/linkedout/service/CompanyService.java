package bg.softuni.linkedout.service;

import bg.softuni.linkedout.model.dto.CreateCompanyDTO;

public interface CompanyService {
    void saveCompany(CreateCompanyDTO company);
}