package uz.pdp.lesson1vaz1.repositrory;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.lesson1vaz1.entity.Address;

public interface AddressRepository extends JpaRepository<Address, Integer> {

    boolean existsByHomeNumberAndStreet(String homeNumber, String street);
}
