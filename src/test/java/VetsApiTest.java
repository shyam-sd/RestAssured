import Vets.api.data.Vet;
import Vets.api.VetsApiClient;
import api.common.ApiResponse;
import api.common.exception.InvalidResponseException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class VetsApiTest {

    static String apiUrl;
    private String id;

    @BeforeAll
    static void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }

//1.0 Used to Get Vets as Response
    @Test
    public void get_Veterinarians() throws InvalidResponseException {
        VetsApiClient client = new VetsApiClient(apiUrl, "/api/vets");
        Vet[] vets = client.getVeterinarians();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(vets[0].getFirstName()).isEqualTo("James");
        softly.assertThat(vets[0].getLastName()).isEqualTo("Carter");
        softly.assertAll();
    }

//2.0 Used to Add/Post Vets
    @Test
    public void post_Veterinarian() throws InvalidResponseException {
        VetsApiClient client = new VetsApiClient(apiUrl, "/api/vets");
        Vet createVet = client.addVeterinarian(Vet.builder().firstName("Monal").lastName("singh").build());
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(createVet.getFirstName()).isEqualTo("Monal");
        softly.assertThat(createVet.getLastName()).isEqualTo("singh");
        softly.assertThat(createVet.getId()).isNotNull();
        id=createVet.getId();
        softly.assertThat(createVet.getSpecialities()).isNull();
        softly.assertAll();
    }

//3.0 Used to Get Vets by ID
    @Test
    public void get_Veterinarian_byID() throws InvalidResponseException {
        VetsApiClient client = new VetsApiClient(apiUrl, "/api/vets");
        Vet createVet = client.addVeterinarian(Vet.builder().firstName("John").lastName("Taylor").build());
        id=createVet.getId();
        VetsApiClient client1 = new VetsApiClient(apiUrl, "/api/vets/"+ id);
        Vet vets = client1.getVeterinarianById().getContent();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(vets.getFirstName()).isEqualTo("John");
        softly.assertThat(vets.getLastName()).isEqualTo("Taylor");
        softly.assertAll();
    }

//4.0 Used to Delete Vets by ID
    @Test
    public void delete_Veterinarian_byId() throws InvalidResponseException {
        VetsApiClient client = new VetsApiClient(apiUrl, "/api/vets");
        Vet createVet = client.addVeterinarian(Vet.builder().firstName("Mark").lastName("Anderson").build());
        id=createVet.getId();
        VetsApiClient client2 = new VetsApiClient(apiUrl, "/api/vets/" +id);
        ApiResponse <Vet>  deleteId = client2.deleteVeterinarianById();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(deleteId.getHttpStatusCode().equals(204));
        softly.assertAll();
    }
}
