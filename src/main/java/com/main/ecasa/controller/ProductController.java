package com.main.ecasa.controller;



import com.main.ecasa.model.Answer;
import com.main.ecasa.model.Product;

import com.main.ecasa.service.ProductService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping (path = "api/ecasa/product")
@Api(value = "ProductController", description = "News class with Operations GET, POST, PUT, DELETE")
public class ProductController {

   @Autowired
    ProductService productService;




    @ApiOperation(value = "Return list of Products entity", notes = "", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of products has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),
            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
    @GetMapping
  public List<Product>ListProducts(){return productService.findAll();
  }




    @ApiOperation(value = "Create new object products ", notes = "", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of products has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),
            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })

  @PostMapping(path = "{idCategory}",produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Answer> create (@PathVariable("idSection") Long idCategory, @RequestBody Product product){

      Answer res = new Answer();
      try {
    productService.addProduct(product,idCategory);
res.setResponse("Successful");
    return new ResponseEntity<Answer>(res, HttpStatus.OK);
}catch (EntityNotFoundException e){
    res.setResponse("Error");
    return new ResponseEntity<Answer>(res, HttpStatus.NOT_FOUND);
}

}


    @ApiOperation(value = "Return list of products with same section", notes = "", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of products has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),

            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
   @GetMapping(path = "/category/{idCategory}")
    public  List<Product> findProductCategory (@PathVariable("idCategory") Long idCategory){

     return productService.findIdCategory(idCategory);
    }

    @ApiOperation(value = "Return list of products filtered by lot", notes = "", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of products has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),

            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
    @GetMapping(path = "/lot/{lot}")
    public  List<Product> findProductLot (@PathVariable("lot") String lot){

        return productService.findLot(lot);
    }
    @ApiOperation(value = "Return list of products filtered by color", notes = "", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of products has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),

            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
    @GetMapping(path = "/color/{color}")
    public  List<Product> findProductColor (@PathVariable("color") String color){

        return productService.findColor(color);
    }

    @ApiOperation(value = "Return list of products filtered by fragile", notes = "", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of products has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),

            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
    @GetMapping(path = "/fragile/{fragile}")
    public  List<Product> findProductFragile (@PathVariable("fragile") String fragile){

        return productService.findFragile(fragile);
    }

    @ApiOperation(value = "Return list of products filtered by price range", notes = "", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of products has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),
            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
    @GetMapping(path = "/price/{priceStart}/{priceEnd}")
    public  List<Product> findProductPrice (@PathVariable("priceStart") float priceStart,@PathVariable("priceEnd") float priceEnd){

        return productService.findPrice(priceStart,priceEnd);
    }

    @ApiOperation(value = "Return list of products filtered by type", notes = "", response = Product.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of products has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),

            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
    @GetMapping(path = "/type/{type}")
    public  List<Product> findProductType (@PathVariable("type") String type){

        return productService.findType(type);
    }
}
