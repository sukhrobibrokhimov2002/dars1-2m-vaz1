package uz.pdp.lesson1vaz1.repositrory;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1vaz1.entity.Worker;

public interface WorkerRepository extends JpaRepository<Worker,Integer> {

    boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByPhoneNumberAndIdNot(String phoneNumber, Integer id);
}
