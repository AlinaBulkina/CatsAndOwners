package repositories;

import entity.Owner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface IOwnerRepository extends JpaRepository<Owner, Integer> {
}
