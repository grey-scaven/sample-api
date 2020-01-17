package com.grey.sample.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.List;

@Entity
@Table(catalog = "test", name = "samples")
@Getter
@Setter
@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
public class Sample extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Long version;

    private Long code;

    private String userId;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "sampleId", insertable = false, updatable = false)
    private List<SampleItem> sampleItemList;

}
