package app.repository;

import app.model.Movie;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.UUID;

public interface MovieRepository extends CrudRepository<Movie, UUID> {
    List<Movie> findByTitleContainingOrDescriptionContainingOrCategoryContainingIgnoreCase(
            String title, String description, String category
    );

}
