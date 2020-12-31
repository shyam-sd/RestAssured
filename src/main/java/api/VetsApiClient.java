package api;

import api.Vets.Vets;
import api.common.ApiClient;
import api.common.ApiRequest;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import com.google.gson.GsonBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.Method;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.mapper.ObjectMapperType;
import org.junit.jupiter.api.Disabled;

public class VetsApiClient extends ApiClient {

    public VetsApiClient(String baseUrl) {
        super(baseUrl, "/api/vets");

        ObjectMapperConfig config = new ObjectMapperConfig(ObjectMapperType.GSON)
                .gsonObjectMapperFactory((type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
        setObjectMapper(new GsonMapper(config.gsonObjectMapperFactory()));
    }

    public Vets[] getVet() throws InvalidResponseException {
        ApiResponse<Vets[]> response = caller.executeRequest(getRequest(), Method.GET, Vets[].class);
        return response.getContent();
    }

    public Vets create(Vets vet) throws InvalidResponseException {
        ApiRequest request = getRequest().withBody(vet).withHeader("Content-Type", "application/json");
        ApiResponse<Vets> response = caller.executeRequest(getRequest(), Method.POST, Vets.class);
        return response.getContent();
    }


}


