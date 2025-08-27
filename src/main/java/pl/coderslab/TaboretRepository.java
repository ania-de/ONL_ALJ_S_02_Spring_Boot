package pl.coderslab;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaboretRepository extends JpaRepository<Taboret,Long> {
}
