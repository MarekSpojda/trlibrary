package pl.marekspojda.TrLibrary.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import pl.marekspojda.TrLibrary.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	@Query("select u from User u  left join fetch u.books where u.email=?1")
	User findUserByEmailCustom(String email);

	@Query("select u from User u  left join fetch u.books where u.name=?1 and u.surname=?2 and u.birthDate=?3")
	User findUserByNameSurnameBirthdateCustom(String name, String surname, Date birthdate);

	Optional<User> findUserByEmail(String email);

	boolean existsByEmail(String email);
}