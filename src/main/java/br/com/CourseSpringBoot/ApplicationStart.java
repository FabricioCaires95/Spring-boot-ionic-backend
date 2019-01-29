package br.com.CourseSpringBoot;

import br.com.CourseSpringBoot.domain.Category;
import br.com.CourseSpringBoot.domain.Product;
import br.com.CourseSpringBoot.repositories.CategoryRepository;
import br.com.CourseSpringBoot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

/**
 * @author fabricio
 */

@SpringBootApplication
public class ApplicationStart implements CommandLineRunner {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ProductRepository productRepository;

    public static void main(String[] args){

        SpringApplication.run(ApplicationStart.class, args);

    }

    @Override
    public void run(String... args) throws Exception {

        Category c1 = new Category(null, "Eletronics");
        Category c2 = new Category(null, "Office");

        Product p1 = new Product(null, "Computer", 2000.00);
        Product p2 = new Product(null, "Printer", 800.00);
        Product p3 = new Product(null, "Mouse", 80.00);

        c1.getProducts().addAll(Arrays.asList(p1,p2,p3));
        c2.getProducts().addAll(Arrays.asList(p2));

        p1.getCategories().addAll(Arrays.asList(c1));
        p2.getCategories().addAll(Arrays.asList(c1,c2));
        p3.getCategories().addAll(Arrays.asList(c1));

        categoryRepository.saveAll(Arrays.asList(c1,c2));
        productRepository.saveAll(Arrays.asList(p1,p2,p3));


    }
}
