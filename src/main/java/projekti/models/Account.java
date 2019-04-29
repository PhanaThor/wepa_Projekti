package projekti.models;

import javax.persistence.Entity;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import lombok.NoArgsConstructor;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(name = "Account.picturePictures", attributeNodes = {@NamedAttributeNode("pictures"), @NamedAttributeNode("profilePicture")})
public class Account extends AbstractPersistable<Long> {
    @NotEmpty
    @Size(min = 4, max = 10)
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    @Size(min = 4, max = 15)
    private String profileName;
    @NotEmpty
    private String name;

    @OneToMany(mappedBy = "owner")
    private List<AccountPicture> pictures;

    @OneToOne(mappedBy ="owner")
    private AccountPicture profilePicture;
}
