package Vets.api.data;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Speciality {

    @Expose
    private String id;

    @Expose
    private String name;

}
