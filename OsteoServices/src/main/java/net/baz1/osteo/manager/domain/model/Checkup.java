package net.baz1.osteo.manager.domain.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

/**
 * Created by Clément Bazin on 05/06/16.
 */

@Data
public class Checkup {

    @DBRef
    private CheckupCategory checkupCategory;

    private String contents;
}
