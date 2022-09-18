package com.main.ecasa.controller;

import com.main.ecasa.model.Category;

import com.main.ecasa.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "api/ecasa/category")
@Api(value = "CategoryController", description = "News class with Operations GET, POST, PUT, DELETE")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @ApiOperation(value = "Return list of category entity", notes = "", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of category has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),
            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
@GetMapping
public List<Category> listCategory (){
   return categoryService.findSection();
}


    @ApiOperation(value = "Create new object category ", notes = "", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of category has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),
            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
@PostMapping
 public void create (@RequestBody Category category){
    categoryService.create(category);
}


    @ApiOperation(value = "Update an object category ", notes = "", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of category has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),
            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
@PutMapping(path ="{idCategory}")
    public void update (@PathVariable("idCategory") Long idCategory,
                        @RequestParam(required = false) String name_category,
                        @RequestParam(required = false) float area,
                        @RequestParam(required = false) String type_product){

  categoryService.updateSection(idCategory,name_category,area,type_product);

}

    @ApiOperation(value = "Delete an object category ", notes = "", response = Category.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of category has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),
            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
    @DeleteMapping(path ="{idCategory}")
    public void delete (@PathVariable("idCategory") Long idCategory){

        categoryService.deleteSection(idCategory);

    }


}
