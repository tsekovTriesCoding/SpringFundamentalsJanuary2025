package bg.softuni.linkedout.service.impl;

import bg.softuni.linkedout.model.Company;
import bg.softuni.linkedout.model.dto.CreateCompanyDTO;
import bg.softuni.linkedout.repository.CompanyRepository;
import bg.softuni.linkedout.service.CompanyService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
        this.modelMapper = new ModelMapper();
    }

    public void saveCompany(CreateCompanyDTO createCompanyDTO) {
        Company company = (Company)this.modelMapper.map(createCompanyDTO, Company.class);
        this.companyRepository.saveAndFlush(company);
    }
}