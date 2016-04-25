/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.config;

import com.fasterxml.classmate.TypeResolver;
import com.mangofactory.swagger.configuration.SpringSwaggerConfig;
import com.mangofactory.swagger.models.alternates.AlternateTypeRule;
import com.mangofactory.swagger.models.alternates.Alternates;
import com.mangofactory.swagger.models.alternates.WildcardType;
import com.mangofactory.swagger.plugin.EnableSwagger;
import com.mangofactory.swagger.plugin.SwaggerSpringMvcPlugin;
import com.wordnik.swagger.model.ApiInfo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
 
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
                "Users API",
                "Your user database!",
                "http://userweb.userapi.com/Terms_of_service",
                "userapi.manager@gmail.com",
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html"
        );
    }
    
    
}