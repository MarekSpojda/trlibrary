package pl.marekspojda.TrLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.marekspojda.TrLibrary.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}