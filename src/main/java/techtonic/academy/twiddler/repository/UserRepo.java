package techtonic.academy.twiddler.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import techtonic.academy.twiddler.entity.User;

// The User Repo interacts directly with the database
@Repository
public interface UserRepo extends CrudRepository<User, Long> {
    Page<User> findAll(Pageable pageable);
    User findUserByUsername(String username);
    User findUserById(Long id);
}
