package Entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.*;

@Entity
@Table(catalog = "test", name = "sample_items")
@Getter
@Setter
@SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})
public class SampleItem extends AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sampleId")
    private Sample sample;

    private String itemName;

}
