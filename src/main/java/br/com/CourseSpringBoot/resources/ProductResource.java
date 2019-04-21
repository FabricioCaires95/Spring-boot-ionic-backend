package br.com.CourseSpringBoot.resources;


import br.com.CourseSpringBoot.domain.Product;
import br.com.CourseSpringBoot.dto.ProductDTO;
import br.com.CourseSpringBoot.resources.utils.URL;
import br.com.CourseSpringBoot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author fabricio
 */
@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "0") String name,
            @RequestParam(value = "categories", defaultValue = "0") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
            @RequestParam(value = "orderby", defaultValue = "name")String orderby,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction){

        String nameDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        Page<Product> list = productService.search(nameDecoded, ids, page , linesPerPage, orderby, direction);
        Page<ProductDTO> pageDto = list.map(obj -> new ProductDTO(obj));

        return ResponseEntity.ok(pageDto);
    }


}
