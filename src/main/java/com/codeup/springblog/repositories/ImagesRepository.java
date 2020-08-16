package com.codeup.springblog.repositories;

import com.codeup.springblog.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImagesRepository extends JpaRepository<Image, Long> {

}
