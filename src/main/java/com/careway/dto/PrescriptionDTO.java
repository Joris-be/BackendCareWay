package com.careway.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class PrescriptionDTO {
    private Integer idprescription;
    private String motifmedical;
    private String typetransport;
    private Date dateprescription;
    private Date dategeneration;
    private Integer idmedecin;
    private Integer idpatient;
}
