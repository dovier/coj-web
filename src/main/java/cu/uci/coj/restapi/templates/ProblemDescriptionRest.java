/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cu.uci.coj.restapi.templates;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;
import java.util.List;

/**
 *
 * @author lucy
 */
@ApiModel
public class ProblemDescriptionRest {
    
    @ApiModelProperty(value = "Creado por", required = true)
    String createdby;
    @ApiModelProperty(value = "Fuente", required = true)
    String source;
    @ApiModelProperty(value = "Autor", required = true)
    String author;    
    @ApiModelProperty(value = "Adicionado al COJ por", required = true)
    String addedby;
    @ApiModelProperty(value = "Fecha de creación", required = true)
    String dateOfCreation;
    @ApiModelProperty(value = "Todos los tiempos totales de cada lenguaje", required = true)
    List<Long> totaltime;
    @ApiModelProperty(value = "Todos los tiempos de los casos por cada lenguaje", required = true)
    List<Long> testtime;
    @ApiModelProperty(value = "Memoria asignada por cada lenguaje", required = true)
    List<String> memory;
    @ApiModelProperty(value = "Salida límite", required = true)
    String outputMB;
    @ApiModelProperty(value = "Tamaño", required = true)
    List<String> size;     
    @ApiModelProperty(value = "Lenguajes habilitados, ej: [java,c++,bash]", required = true)
    List<String> enabledlanguages;
    @ApiModelProperty(value = "Descripción", required = true)
    String description;
    @ApiModelProperty(value = "Especificación de entrada", required = true)
    String inputSpecification;
    @ApiModelProperty(value = "Especificación de salida", required = true)
    String outputSpecification;
    @ApiModelProperty(value = "Ejemplo de entrada", required = true)
    String sampleInput;
    @ApiModelProperty(value = "Ejemplo de salida", required = true)
    String sampleOutput;
    @ApiModelProperty(value = "Sugerencia(s)", required = true)
    String hints;
    @ApiModelProperty(value = "Recomendación", required = true)
    List<String> recommendation;

    public ProblemDescriptionRest(String createdby, String source, String author, String addedby, String dateOfCreation, List<Long> totaltime, List<Long> testtime, List<String> memory, String outputMB, List<String> size, List<String> enabledlanguages, String description, String inputSpecification, String outputSpecification, String sampleInput, String sampleOutput, String hints, List<String> recommendation) {
        this.createdby = createdby;
        this.source = source;
        this.author = author;
        this.addedby = addedby;
        this.dateOfCreation = dateOfCreation;
        this.totaltime = totaltime;
        this.testtime = testtime;
        this.memory = memory;
        this.outputMB = outputMB;
        this.size = size;
        this.enabledlanguages = enabledlanguages;
        this.description = description;
        this.inputSpecification = inputSpecification;
        this.outputSpecification = outputSpecification;
        this.sampleInput = sampleInput;
        this.sampleOutput = sampleOutput;
        this.hints = hints;
        this.recommendation = recommendation;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getAddedby() {
        return addedby;
    }

    public void setAddedby(String addedby) {
        this.addedby = addedby;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(String dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }

    public List<Long> getTotaltime() {
        return totaltime;
    }

    public void setTotaltime(List<Long> totaltime) {
        this.totaltime = totaltime;
    }

    public List<Long> getTesttime() {
        return testtime;
    }

    public void setTesttime(List<Long> testtime) {
        this.testtime = testtime;
    }

    public List<String> getMemory() {
        return memory;
    }

    public void setMemory(List<String> memory) {
        this.memory = memory;
    }

    public String getOutputMB() {
        return outputMB;
    }

    public void setOutputMB(String outputMB) {
        this.outputMB = outputMB;
    }

    public List<String> getSize() {
        return size;
    }

    public void setSize(List<String> size) {
        this.size = size;
    }

    public List<String> getEnabledlanguages() {
        return enabledlanguages;
    }

    public void setEnabledlanguages(List<String> enabledlanguages) {
        this.enabledlanguages = enabledlanguages;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInputSpecification() {
        return inputSpecification;
    }

    public void setInputSpecification(String inputSpecification) {
        this.inputSpecification = inputSpecification;
    }

    public String getOutputSpecification() {
        return outputSpecification;
    }

    public void setOutputSpecification(String outputSpecification) {
        this.outputSpecification = outputSpecification;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }

    public String getSampleOutput() {
        return sampleOutput;
    }

    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    public String getHints() {
        return hints;
    }

    public void setHints(String hints) {
        this.hints = hints;
    }

    public List<String> getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(List<String> recommendation) {
        this.recommendation = recommendation;
    }
    
    

    

}
