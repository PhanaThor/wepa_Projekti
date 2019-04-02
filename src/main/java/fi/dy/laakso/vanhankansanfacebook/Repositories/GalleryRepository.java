package fi.dy.laakso.vanhankansanfacebook.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.dy.laakso.vanhankansanfacebook.Models.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    
}