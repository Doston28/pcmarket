package uz.pdp.pcmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.pcmarket.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, Integer> {
}
