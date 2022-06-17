package com.ademiju.demoThymeLeaf.datas.repositories;

import com.ademiju.demoThymeLeaf.datas.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
