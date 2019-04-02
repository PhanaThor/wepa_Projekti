package fi.dy.laakso.vanhankansanfacebook.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.dy.laakso.vanhankansanfacebook.Models.UserImage;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
    
}