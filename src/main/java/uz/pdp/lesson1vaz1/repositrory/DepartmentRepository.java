package uz.pdp.lesson1vaz1.repositrory;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1vaz1.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    boolean existsByNameAndCompany_Id(String name, Integer company_id);
    boolean existsByNameAndIdNot(String name, Integer id);
}
