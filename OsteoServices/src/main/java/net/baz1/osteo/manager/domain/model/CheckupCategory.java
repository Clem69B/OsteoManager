package net.baz1.osteo.manager.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by Clément Bazin on 05/06/16.
 */

@Data
public class CheckupCategory {

    @Id
    private String id;

    private String name;

    public CheckupCategory(String name) {
        this.name = name;
    }
}
