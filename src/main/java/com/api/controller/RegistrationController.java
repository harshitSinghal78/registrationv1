package com.api.controller;

import com.api.entity.Registration;
import com.api.payload.RegistrationDto;
import com.api.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/registration")
public class RegistrationController {

    private RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    //   http://localhost:8080/api/v1/registration
    @GetMapping
    public ResponseEntity<List<RegistrationDto>> getAllRegistrations(){
        List<RegistrationDto> dtos = registrationService.getRegistrations();
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }


//    http://localhost:8080/api/v1/registration
    @PostMapping
    //public ResponseEntity<RegistrationDto> createRegistration(

    //when there are multiple types of data returning change return type in responseEntity to ? or Object
    public ResponseEntity<?> createRegistration(
//            without @RequestBody we can take JSON and put into Entity object
          @Valid @RequestBody RegistrationDto registrationDto,
          //if any error occur during the validation those error message can be goat from this object
          BindingResult result
    ){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(), HttpStatus.CREATED);
        }
        RegistrationDto regDto = registrationService.createRegistration(registrationDto);
        return new ResponseEntity<>(regDto, HttpStatus.CREATED);
    }

//  http://localhost:8080/api/v1/registration?id=1
    // use in url is query parameter
    @DeleteMapping
    public ResponseEntity<String> deleteRegistration(
            @RequestParam long id
        ){
        registrationService.deleteRegistration(id);
        return new ResponseEntity<>("Record is deleted", HttpStatus.OK);
    }


//    http://localhost:8080/api/v1/registration/2
//        IN this we use path parameter and hume ye @putingmapping mai batana hoga ki humne id supply kri hai
    @PutMapping("/{id}")
    public ResponseEntity<Registration> updateRegistration(
            @PathVariable long id,
            @RequestBody Registration registration
        ){
        Registration updateReg = registrationService.updateRegistration(id, registration);
        return new ResponseEntity<>(updateReg, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistrationDto> getRegistrationById(
            @PathVariable long id
    ){
        RegistrationDto dto =  registrationService.getRegistrationById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
