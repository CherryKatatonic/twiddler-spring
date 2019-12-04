package techtonic.academy.twiddler.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import techtonic.academy.twiddler.entity.Twiddle;

// The Twiddle Repo interacts directly with the database
@Repository
public interface TwiddleRepo extends CrudRepository<Twiddle, Long> {
    Page<Twiddle> findAll(Pageable pageable);
}
