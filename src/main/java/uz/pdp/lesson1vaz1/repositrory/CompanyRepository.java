package uz.pdp.lesson1vaz1.repositrory;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1vaz1.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer> {
    boolean existsByCorpName(String corpName);
    boolean existsByCorpNameAndIdNot(String corpName, Integer id);
}
