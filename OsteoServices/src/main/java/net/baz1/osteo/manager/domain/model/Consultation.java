package net.baz1.osteo.manager.domain.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;
import java.util.List;

/**
 * Created by Clément Bazin on 03/02/15.
 */

@Data
public class Consultation {

    @Id
    private String id;

    @DBRef
    private Patient patient;
    
    private Date dateConsultation;
    private Date lastOsteopathConsultation;

    private String reason;
    private List<Checkup> checkups;
    private String ongoingTreatment;

    protected Date createdDate;
    protected Date lastUpdatedDate;
}
