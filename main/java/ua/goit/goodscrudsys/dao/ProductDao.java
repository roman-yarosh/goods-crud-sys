package ua.goit.goodscrudsys.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.goit.goodscrudsys.entity.Product;

/**
 * Created by yarosh_ra on 18.08.2017.
 */
interface ProductDao extends JpaRepository<Product, Long> {
}
