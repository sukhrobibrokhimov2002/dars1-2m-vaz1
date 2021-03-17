package uz.pdp.lesson1vaz1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Worker {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String fullName;
    @Column(nullable = false, unique = true)
    private String phoneNumber;
    @OneToOne
    private Address address;
    @ManyToOne
    private Department department;
}
