package com.nrkt.tesseractimagetotext.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.Operation;
import io.swagger.v3.oas.models.media.StringSchema;
import io.swagger.v3.oas.models.parameters.Parameter;
import org.springdoc.core.customizers.OperationCustomizer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

@Configuration
@OpenAPIDefinition(info = @Info(title = "Spring Boot OCR Example",
        version = "0.0.1",
        description = "Extracting Text from Image with Tesseract OCR Example Project "))
public class OpenApiConfiguration implements OperationCustomizer {

    @Override
    public Operation customize(Operation operation, HandlerMethod handlerMethod) {
        var parameterHeader = new Parameter()
                .in(ParameterIn.QUERY.toString())
                .description("Enter media type: JSON or XML. (Default: XML) if response media type is \"text/plain\" this part will not be processed ")
                .required(false)
                .name("mediaType")
                .schema(new StringSchema()
                        .addEnumItem("xml")
                        .addEnumItem("json"));//._default("xml"));

        operation.addParametersItem(parameterHeader);
        return operation;
    }
}
