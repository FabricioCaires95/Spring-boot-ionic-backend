package br.com.CourseSpringBoot.resources;


import br.com.CourseSpringBoot.domain.Product;
import br.com.CourseSpringBoot.dto.ProductDTO;
import br.com.CourseSpringBoot.resources.utils.URL;
import br.com.CourseSpringBoot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author fabricio
 */
@RestController
@RequestMapping("/product")
public class ProductResource {

    @Autowired
    private ProductService productService;

//    @GetMapping("/{id}")
//    public ResponseEntity<Product> find(@PathVariable Integer id){
//        Product p = productService.findById(id);
//        return ResponseEntity.ok().body(p);
//    }

    @GetMapping()
    public ResponseEntity<Page<ProductDTO>> findPage(
            @RequestParam(value = "name", defaultValue = "") String name,
            @RequestParam(value = "categories", defaultValue = "") String categories,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linesPerPage", defaultValue = "24")Integer linesPerPage,
            @RequestParam(value = "orderby", defaultValue = "name")String orderby,
            @RequestParam(value = "direction", defaultValue = "ASC")String direction){

        String nameDecoded = URL.decodeParam(name);
        List<Integer> ids = URL.decodeIntList(categories);
        System.out.println("NAME DECODED: " + nameDecoded);
        System.out.println("ID: " + ids);
        Page<Product> list = productService.search(nameDecoded, ids, page , linesPerPage, orderby, direction);
        Page<ProductDTO> pageDto = list.map(obj -> new ProductDTO(obj));

        return ResponseEntity.ok().body(pageDto);
    }

    @GetMapping("/{categoria_id}")
    public ResponseEntity<List<ProductDTO>> find(@PathVariable Integer categoria_id){

        List<Product> list = productService.find(categoria_id);
        List<ProductDTO> listDto = list.stream().map(obj -> new ProductDTO(obj)).collect(Collectors.toList());

        return ResponseEntity.ok().body(listDto);

    }

}
