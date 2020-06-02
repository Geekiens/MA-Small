package bookReviewer.periphery.persistence.repository;

import bookReviewer.adapter.out.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepositoryJpa extends JpaRepository<User, Long> {

    User findByUsername(String username);
}




