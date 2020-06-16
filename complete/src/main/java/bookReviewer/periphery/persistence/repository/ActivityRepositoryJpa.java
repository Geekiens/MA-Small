package bookReviewer.periphery.persistence.repository;

import bookReviewer.adapter.out.persistence.model.Activity;
import bookReviewer.adapter.out.persistence.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepositoryJpa extends JpaRepository<Activity, Long> {
    List<Activity> findAllByUser(User user);
}
