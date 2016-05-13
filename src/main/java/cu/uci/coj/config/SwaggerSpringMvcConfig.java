/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.config;

import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
 
@Configuration
@EnableSwagger
public class SwaggerSpringMvcConfig {
 
    private SpringSwaggerConfig springSwaggerConfig;

 
    @Autowired
    public void setSpringSwaggerConfig(SpringSwaggerConfig springSwaggerConfig) {
        this.springSwaggerConfig = springSwaggerConfig;
    }
 
    @Bean
    public SwaggerSpringMvcPlugin customImplementation() {
//        TypeResolver typeResolver = new TypeResolver();
//        AlternateTypeRule rules = Alternates.newRule(
//                    typeResolver.resolve(ResponseEntity.class, WildcardType.class), 
//                    //with List<T> for any T
//                    typeResolver.resolve(List.class, WildcardType.class));
        
        return new SwaggerSpringMvcPlugin(this.springSwaggerConfig)
                .apiInfo(apiInfo())
                .includePatterns(".*/*.*")
                .genericModelSubstitutes(List.class);
    }
 
    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Capa de Servicios Web para el Juez en Línea Caribeño",
                "Versión 1.0",
                "http://coj.uci.cu/general/about.xhtml",
                "coj@uci.cu",
                "Universidad de las Ciencias Informáticas",
                "http://www.uci.cu"
        );
    }
    
    
}