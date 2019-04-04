package projekti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projekti.models.UserImage;

public interface UserImageRepository extends JpaRepository<UserImage, Long> {
    
}