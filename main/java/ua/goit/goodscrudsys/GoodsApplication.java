package ua.goit.goodscrudsys;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.goit.goodscrudsys.configuration.ModelConfiguration;
import ua.goit.goodscrudsys.dao.ProductService;
import ua.goit.goodscrudsys.entity.Product;

/**
 * Created by yarosh_ra on 18.08.2017.
 */
public class GoodsApplication {

    public static void main(String[] args) {
        Logger logger = LoggerFactory.getLogger(GoodsApplication.class);
        try(AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ModelConfiguration.class)) {
            ProductService productService = context.getBean(ProductService.class);

            logger.info("Staring product application!");
            Product product = new Product();
            product.setName("Name pro1");
            product.setDescription("Desc");
            product.setManufacturer("Manuf");

            productService.save(product);

            productService.findAll().forEach(System.out::println);

        }
    }

}
