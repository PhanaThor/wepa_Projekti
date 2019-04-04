package projekti.models;

import javax.persistence.Entity;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserImage extends AbstractPersistable<Long> {
    @Lob
    private byte[] data;
    private String description;
    @ManyToOne
    private Gallery gallery;
}