package com.cdac.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductReqDto {

	@NotBlank
    @Size(min = 2, max = 255)
    private String name;

    @NotNull
    @Size(min = 2, max = 255)
    private String category;

    @NotNull
    @Min(1)
    private Double sellingPrice;

    @NotNull
    @Min(1)
    private Double costPrice;

    @NotNull
    @Min(0)
    private Integer inStock;

    @NotNull
    private Double discount;

    @Size(max = 500)
    private String description;

    @NotNull
    private Double gst;
}
