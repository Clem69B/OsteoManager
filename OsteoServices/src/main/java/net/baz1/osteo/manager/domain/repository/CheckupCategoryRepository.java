package net.baz1.osteo.manager.domain.repository;

import net.baz1.osteo.manager.domain.model.CheckupCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by Clément Bazin on 18/06/16.
 */
public interface CheckupCategoryRepository extends MongoRepository<CheckupCategory, String> {

    List<CheckupCategory> findByNameIgnoreCase(String name);

}
