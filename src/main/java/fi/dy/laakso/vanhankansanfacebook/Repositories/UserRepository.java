package fi.dy.laakso.vanhankansanfacebook.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import fi.dy.laakso.vanhankansanfacebook.Models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}