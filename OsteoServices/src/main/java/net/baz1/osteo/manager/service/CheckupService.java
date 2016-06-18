package net.baz1.osteo.manager.service;

import net.baz1.osteo.manager.domain.model.CheckupCategory;
import net.baz1.osteo.manager.domain.repository.CheckupCategoryRepository;
import net.baz1.osteo.manager.exceptions.CheckupCategoryNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Clément Bazin on 15/02/15.
 */

@Component
public class CheckupService {

    @Autowired
    CheckupCategoryRepository checkupCategoryRepository;

    public CheckupCategory newCategory(String name) {

        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("name of the category should be defined");
        }

        CheckupCategory category = new CheckupCategory(name);

        return this.checkupCategoryRepository.save(category);
    }

    public List<CheckupCategory> getAllCategories() {
        return this.checkupCategoryRepository.findAll();
    }

    public List<CheckupCategory> findCategories(String name) {
        return this.checkupCategoryRepository.findByNameIgnoreCase(name);
    }

    public CheckupCategory updateCategory(CheckupCategory checkupCategory) throws CheckupCategoryNotFoundException {
        if (this.checkupCategoryRepository.findOne(checkupCategory.getId()) == null) {
            throw new CheckupCategoryNotFoundException("This checkup category doesn't exist");
        }
        return this.checkupCategoryRepository.save(checkupCategory);
    }

    public void removeCategory(String categoryId) {
        this.checkupCategoryRepository.delete(categoryId);
    }

}
