package repositories;

import entity.Cat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICatRepository extends JpaRepository<Cat, Integer> {
}
