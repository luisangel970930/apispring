package com.main.ecasa.controller;

import com.main.ecasa.model.Section;

import com.main.ecasa.service.SectionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping(path = "api/ecasa/section")
@Api(value = "SectionController", description = "News class with Operations GET, POST, PUT, DELETE")
public class SectionController {

    @Autowired
    SectionService sectionService;

    @ApiOperation(value = "Return list of Sections entity", notes = "", response = Section.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of sections has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),
            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
@GetMapping
public List<Section> listSection (){
   return sectionService.findSection();
}


    @ApiOperation(value = "Create new object section ", notes = "", response = Section.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of secciones has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),
            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
@PostMapping
 public void create (@RequestBody Section section){
    sectionService.create(section);
}


    @ApiOperation(value = "Update an object section ", notes = "", response = Section.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of sections has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),
            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
@PutMapping(path ="{idSection}")
    public void update (@PathVariable("idSection") Long idSection,
                        @RequestParam(required = false) String name_section,
                        @RequestParam(required = false) float area,
                        @RequestParam(required = false) String type_product){

  sectionService.updateSection(idSection,name_section,area,type_product);

}

    @ApiOperation(value = "Delete an object section ", notes = "", response = Section.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "The request has been successful. The list of sections has been obtained"),
            @ApiResponse(code = 500, message = "The server encountered an unexpected condition that prevented it from fulfilling the request."),
            @ApiResponse(code = 400, message = "The server can not or will not process the request due to something that is perceived as a client error (for example, the syntax of the request)") })
    @DeleteMapping(path ="{idSection}")
    public void delete (@PathVariable("idSection") Long idSection){

        sectionService.deleteSection(idSection);

    }


}
