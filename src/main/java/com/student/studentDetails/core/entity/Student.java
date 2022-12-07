package com.student.studentDetails.core.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "STUDENT")
@Setter
@Getter
@ToString
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "STUDENT_ID")
    private Long studentId;

    @Column(name = "EMAIL_ID")
    private String emailId;

    @Column(name = "STUDENT_NAME")
    private String studentName;

    @Column(name = "STUDENT_CLASS")
    private String studentClass;

    @Column(name = "MOBILE")
    private String mobile;

    @Column(name = "ACTIVE_STATUS")
    private boolean activeStatus;

    @Column(name = "CREATED_AT")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "CREATED_BY")
    private String createdBy;

    @Column(name = "MODIFIED_AT")
    @UpdateTimestamp
    private Date modifiedAt;

    @Column(name = "MODIFIED_BY")
    private String modifiedBy;

    }
