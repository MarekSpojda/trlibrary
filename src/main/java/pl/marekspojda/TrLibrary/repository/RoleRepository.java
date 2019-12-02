package pl.marekspojda.TrLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pl.marekspojda.TrLibrary.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}