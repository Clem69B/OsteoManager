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
import org.springframework.data.annotation.Id;

/**
 * Created by Clément Bazin on 14/12/14.
 */
@Data
abstract public class Human {

    @Id
    protected String id;

    protected String firstName;
    protected String lastName;
    protected String street;
    protected int postalCode;
    protected String city;
    protected String phoneNumber;
    protected String emailAddress;

}
