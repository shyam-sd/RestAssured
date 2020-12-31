import api.Vets.Vets;
import api.VetsApiClient;
import api.common.exception.InvalidResponseException;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

public class VetsApiTest {

    static String apiUrl;

    @BeforeAll
    static void getApiUrl() {
        apiUrl = System.getProperty("apiUrl");
    }

//1.0 Get vets
    @Test
    public void getVet_Services() throws InvalidResponseException {
        VetsApiClient client = new VetsApiClient(apiUrl);
        Vets[] vets = client.getVet();
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(vets[0].getFirstName()).isEqualTo("James");
        softly.assertThat(vets[0].getLastName()).isEqualTo("Carter");
        softly.assertAll();
    }
//2.0 Used to Add/Post vets
    @Test
    public void create_vets_without_specialities() throws InvalidResponseException {
        VetsApiClient client = new VetsApiClient(apiUrl);
        Vets createVet = client.create(Vets.builder().firstName("Monal").lastName("singh").build());
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(createVet.getFirstName()).isEqualTo("Monal");
        softly.assertThat(createVet.getLastName()).isEqualTo("singh");
        softly.assertThat(createVet.getId()).isNotNull();
        softly.assertThat(createVet.getSpecialities()).isNull();
        softly.assertAll();
    }


    @Test
    @Disabled
    public void delete_vets() throws InvalidResponseException {
        VetsApiClient client = new VetsApiClient(apiUrl);

    }

}
