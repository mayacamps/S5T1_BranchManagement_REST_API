package cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.repository;

import cat.itacademy.barcelonactiva.camps.maya.s05.t01.n02.model.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchRepository extends JpaRepository<Branch, Integer> {
    Optional<Branch> findByName(String name);
}
