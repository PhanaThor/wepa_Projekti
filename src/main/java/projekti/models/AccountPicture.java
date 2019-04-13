package projekti.models;

import javax.persistence.Entity;
import javax.persistence.Lob;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountPicture extends AbstractPersistable<Long> {
    @Lob
    private byte[] data;
    private String description;
}