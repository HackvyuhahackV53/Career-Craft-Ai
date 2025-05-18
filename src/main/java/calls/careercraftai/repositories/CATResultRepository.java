package calls.careercraftai.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import calls.careercraftai.Entity.CATResult;

@Repository
public interface CATResultRepository extends JpaRepository<CATResult, Integer> {}