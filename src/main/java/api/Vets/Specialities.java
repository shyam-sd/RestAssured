package api.Vets;

import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Specialities {

    @Expose
    private String id;

    @Expose
    private String name;


}
