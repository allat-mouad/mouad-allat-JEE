package ma.enset.ecom.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ma.enset.ecom.entities.Product;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor @NoArgsConstructor
public class CategoryDTO {
    private  long id;
    private String name;

}
