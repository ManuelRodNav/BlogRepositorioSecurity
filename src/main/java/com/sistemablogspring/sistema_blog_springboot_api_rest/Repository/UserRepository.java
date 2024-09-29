package com.sistemablogspring.sistema_blog_springboot_api_rest.Repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sistemablogspring.sistema_blog_springboot_api_rest.Entities.User;
import java.util.List;

public interface  UserRepository  extends JpaRepository<User,Long>{
    Optional<User> findByUsername(String username);
}
