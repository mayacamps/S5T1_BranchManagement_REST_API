package cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "branches", uniqueConstraints = @UniqueConstraint(columnNames = "name"))
public class Branch implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", unique = true)
    private Integer id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "country")
    private String country;

    public Branch(String name, String country) {
        this.name = name;
        this.country = country;
    }
}
