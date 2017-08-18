package ua.goit.goodscrudsys.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.goit.goodscrudsys.entity.Product;

import java.util.List;

@Service
public class ProductService {

    private final ProductDao productDao;

    @Autowired
    public ProductService(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Transactional(readOnly = true)
    public List<Product> findAll(){
        return productDao.findAll();
    }

    @Transactional(readOnly = true)
    public Product getOne(Long id){
        return productDao.getOne(id);
    }

    @Transactional
    public Product save (Product entity) {
        return productDao.save(entity);
    }

    @Transactional
    public void delete (Product entity) {
        productDao.delete(entity);
    }

}
