package com.br.geekstore;

import com.br.geekstore.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@SpringBootApplication
@RestController
@RequestMapping("/")
public class GeekstoreApplication {

  @Autowired
  private RoleService serviceR;

  @GetMapping
  public ModelAndView swaggerUi(){
    serviceR.saveRoles();
    return new ModelAndView("redirect:/swagger-ui/");
  }

	public static void main(String[] args) {
		SpringApplication.run(GeekstoreApplication.class, args);
	}

}
