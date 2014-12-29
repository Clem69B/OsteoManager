/*
 * Copyright (c) 2014. Clément Bazin
 *
 *     This file is part of OstéoManager.
 *
 *     OstéoManager is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     any later version.
 *
 *     OstéoManager is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with Foobar.  If not, see <http://www.gnu.org/licenses/>. 2
 */

package net.baz1.osteo.manager.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.stereotype.Component;

/**
 * Created by Clément Bazin on 14/12/14.
 */

@Component
@Data
@ToString(callSuper = true)
public class MedicalProfessional extends Human {

    @DBRef
    private MedicalProfession profession;

    private String fax;

    public MedicalProfessional(String firstName, String lastName, MedicalProfession profession) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.profession = profession;
    }

}
