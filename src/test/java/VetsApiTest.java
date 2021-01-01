import api.Vets.Vets;
import api.VetsApiClient;
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

    @Test
    public void get_create_delete () throws InvalidResponseException {

        getVet_Services();
        createVets_without_specialities();
        getVets_byID();
        deleteVets_byId();

    }


//1.0 Used to Get Vets as Response
    public void getVet_Services() throws InvalidResponseException {
        VetsApiClient client = new VetsApiClient(apiUrl);
        Vets[] vets = client.getVet();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(vets[0].getFirstName()).isEqualTo("James");
        softly.assertThat(vets[0].getLastName()).isEqualTo("Carter");
        softly.assertAll();
    }

//2.0 Used to Add/Post Vets
    public void createVets_without_specialities() throws InvalidResponseException {
        VetsApiClient client = new VetsApiClient(apiUrl);
        Vets createVet = client.create(Vets.builder().firstName("Monal").lastName("singh").build());
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(createVet.getFirstName()).isEqualTo("Monal");
        softly.assertThat(createVet.getLastName()).isEqualTo("singh");
        softly.assertThat(createVet.getId()).isNotNull();
        id=createVet.getId();
        softly.assertThat(createVet.getSpecialities()).isNull();
        softly.assertAll();
    }

//3.0 Used to Get Vets by ID
    public void getVets_byID() throws InvalidResponseException {
        VetsApiClient client = new VetsApiClient(apiUrl, id);
        Vets vets = client.getById().getContent();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(vets.getFirstName()).isEqualTo("Monal");
        softly.assertThat(vets.getLastName()).isEqualTo("singh");
    }

//4.0 Used to Delete Vets by ID
    public void deleteVets_byId() throws InvalidResponseException {
        VetsApiClient client = new VetsApiClient(apiUrl, id);
        ApiResponse <Vets>  deleteId = client.deleteId();
        ApiResponse <Vets> getVet = client.getById();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(deleteId.getHttpStatusCode().equals(204));
    }

}
