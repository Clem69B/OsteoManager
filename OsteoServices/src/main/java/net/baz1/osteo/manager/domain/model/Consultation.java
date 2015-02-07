package net.baz1.osteo.manager.domain.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by Clément Bazin on 03/02/15.
 */

@Data
public class Consultation {

    private Date dateConsultation;
    private Date lastOsteoConsultation;

    private String reason;
    private String checkup;
    private String treatment;


}
