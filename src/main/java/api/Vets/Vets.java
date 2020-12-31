package api.Vets;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
public class Vets {

    @Expose
    private String firstName;

    @Expose
    private String lastName;

    @Expose
    private Long id;

    @Expose
    private List<Specialities> specialities;

}


