package com.emainfo.cra.repository;

import com.emainfo.cra.generic.GenericRepository;
import com.emainfo.cra.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends GenericRepository<Role, Long> {
	Role findByName(String name);
}
