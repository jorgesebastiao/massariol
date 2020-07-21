package br.com.massariol.domain.common;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
@Getter
@Setter
@MappedSuperclass
public abstract class EntityBaseImpl<T> {

    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;
    @Column(nullable = false)
    private LocalDateTime creationDate;
    @Column(nullable = false)
    private LocalDateTime lastModification;

    protected EntityBaseImpl() {
        creationDate = LocalDateTime.now();
        lastModification = LocalDateTime.now();
    }
}
