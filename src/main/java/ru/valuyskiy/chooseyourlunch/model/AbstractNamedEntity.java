package ru.valuyskiy.chooseyourlunch.model;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class AbstractNamedEntity extends AbstractBaseEntity {

    // TODO разобраться с SafeHtml

    @NotBlank
    @Size(min = 2, max = 100)
    @Column(name = "name", nullable = false)
//    @SafeHtml
    protected String name;

    public AbstractNamedEntity() {
    }

    public AbstractNamedEntity(String name) {
        super(null);
        this.name = name;
    }

    public AbstractNamedEntity(Integer id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
