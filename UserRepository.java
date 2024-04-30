package com.example.oopsassgn;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserSetup, Integer> {
    UserSetup findByMailID(String mailID);
}




