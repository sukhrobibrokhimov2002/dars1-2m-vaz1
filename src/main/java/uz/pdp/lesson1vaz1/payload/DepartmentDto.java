package uz.pdp.lesson1vaz1.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class DepartmentDto {
    @NotNull(message = "name must not be empty")
    private String name;
    private Integer companyId;
}
