package Vets.api;

import api.common.ApiClient;
import api.common.ApiRequest;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import Vets.api.data.Vet;
import com.google.gson.GsonBuilder;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.http.Method;
import io.restassured.internal.mapping.GsonMapper;
import io.restassured.mapper.ObjectMapperType;

public class VetsApiClient extends ApiClient {

    public VetsApiClient(String baseUrl, String basePathOwners) {
        super(baseUrl, basePathOwners);

        ObjectMapperConfig config = new ObjectMapperConfig(ObjectMapperType.GSON)
                .gsonObjectMapperFactory((type, s) -> new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create());
        setObjectMapper(new GsonMapper(config.gsonObjectMapperFactory()));
    }


    public Vet[] getVeterinarians() throws InvalidResponseException {
        ApiResponse<Vet[]> response = caller.executeRequest(getRequest(), Method.GET, Vet[].class);
        return response.getContent();
    }

    public Vet addVeterinarian(Vet vet) throws InvalidResponseException {
        ApiRequest request = getRequest().withBody(vet).withHeader("Content-Type", "application/json");
        ApiResponse<Vet> response = caller.executeRequest(request, Method.POST, Vet.class);
        return response.getContent();
    }

    public ApiResponse <Vet> getVeterinarianById() {
        ApiResponse<Vet> response = caller.executeRequest(getRequest(), Method.GET, Vet.class);
        return response;
    }

    public ApiResponse<Vet> deleteVeterinarianById() {
        ApiResponse<Vet> response = caller.executeRequest(getRequest(), Method.DELETE, Vet.class);
        return response;
    }

}
