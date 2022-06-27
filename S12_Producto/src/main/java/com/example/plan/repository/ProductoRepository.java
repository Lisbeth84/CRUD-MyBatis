package com.example.plan.repository;

import com.example.plan.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author admin
 */
@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>{
    
}
