package com.learnspringboot.product.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Schema(
        name = "Category",
        description = "It holds category information along with their products."
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDTO {
    private Long id;
    @NotBlank(message = "Category name cannot be empty!")
    private String name;
    private List<ProductDTO> products;
}
