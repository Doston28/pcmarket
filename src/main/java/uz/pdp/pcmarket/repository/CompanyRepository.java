package uz.pdp.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pcmarket.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
}
