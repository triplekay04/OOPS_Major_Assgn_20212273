package com.example.oopsassgn;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommRepository extends JpaRepository<Comment, Integer> {
    
}
