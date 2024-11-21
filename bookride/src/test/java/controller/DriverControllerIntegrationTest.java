package controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalTime;

import com.bookride.Main;
import com.bookride.dto.DriverDto;
import com.bookride.model.Enum.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(classes = Main.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DriverControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void testCreateDriver() {
        DriverDto driverDto = new DriverDto(1L, "John", "Doe", Status.AVAILABLE, LocalTime.parse("08:00:00"), LocalTime.parse("18:00:00"), null);
        ResponseEntity<DriverDto> response = restTemplate.postForEntity("/api/chauffeurs/add", driverDto, DriverDto.class);

        assertEquals(201, response.getStatusCodeValue());
        assertNotNull(response.getBody());
        assertEquals("John", response.getBody().getFirstName());
    }

    @Test
    void testGetDriverById() {
        ResponseEntity<DriverDto> response = restTemplate.exchange("/api/drivers/1", HttpMethod.GET, null, DriverDto.class);

        assertEquals(200, response.getStatusCodeValue());
        assertNotNull(response.getBody());
    }
}
