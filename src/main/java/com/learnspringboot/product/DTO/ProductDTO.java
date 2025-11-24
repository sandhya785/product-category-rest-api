package com.learnspringboot.product.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Schema(
        name = "Product",
        description = "It holds product information."
)

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
    private Long id;
    @NotBlank(message = "Product name cannot be empty!")
    private String name;
    private String description;
    @Min(value=1, message = "Product price must be at least 1")
    private Double price;
    @NotNull(message = "Category ID is required!")
    private Long categoryId;
}
