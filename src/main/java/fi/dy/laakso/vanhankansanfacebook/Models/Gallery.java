package fi.dy.laakso.vanhankansanfacebook.Models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.data.jpa.domain.AbstractPersistable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Gallery extends AbstractPersistable<Long> {
    @OneToOne
    private User owner;
    @OneToMany(mappedBy = "gallery")
    private List<UserImage> images = new ArrayList<>();
}