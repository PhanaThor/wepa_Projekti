package projekti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import projekti.models.Gallery;

public interface GalleryRepository extends JpaRepository<Gallery, Long> {
    
}