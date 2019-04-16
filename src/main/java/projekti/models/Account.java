package projekti.models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

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
    private String username;
    private String password;
    private String profileName;
    private String name;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AccountPicture> pictures;

    @OneToOne(mappedBy ="owner")
    private AccountPicture profilePicture;
}
