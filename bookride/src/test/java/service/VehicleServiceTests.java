package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import com.bookride.Main;
import com.bookride.dto.VehicleDto;
import com.bookride.model.Vehicle;
import com.bookride.model.Enum.Status;
import com.bookride.model.Enum.VehicleType;
import com.bookride.repository.VehicleRepository;
import com.bookride.service.VehicleService;

import lombok.extern.log4j.Log4j2;

@Log4j2
@TestPropertySource(locations = "classpath:application-dev.yaml")
@SpringBootTest(classes = Main.class)
@Transactional
public class VehicleServiceTests {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private VehicleRepository vehicleRepository;

    private Vehicle vehicle;

    @BeforeEach
    void setup() {
        vehicle = new Vehicle();
        vehicle.setModel("Corolla");
        vehicle.setRegistrationNumber("2334-BF100");
        vehicle.setMileage(10000);
        vehicle.setStatus(Status.AVAILABLE);
        vehicle.setVehicleType(VehicleType.BERLINE);
    }

    @Test
    public void testCreateVehicle() {

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        assertNotNull(savedVehicle.getId(), "Vehicle ID should not be null after saving");
        assertEquals("Corolla", savedVehicle.getModel());
    }

    @Test
    public void testReadVehicle() {

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        VehicleDto foundVehicle = vehicleService.getById(savedVehicle.getId());
        assertTrue(foundVehicle != null, "Vehicle should be found by ID");
        assertEquals("Corolla", foundVehicle.getModel(), "Titles should match");
    }

    @Test
    public void testUpdateVehicle() {

        Vehicle savedVehicle = vehicleRepository.save(vehicle);
        savedVehicle.setModel("4x4 Corolla");
        VehicleDto updatedVehicle = vehicleService.update(savedVehicle.getId(), savedVehicle);
        assertEquals("4x4 Corolla", updatedVehicle.getModel(), "Title should be updated");
    }

    @Test
    public void testSoftDeleteVehicle() {

        Vehicle savedVehicle = vehicleRepository.save(vehicle);

        vehicleService.delete(savedVehicle.getId());

        assertThrows(RuntimeException.class, () -> vehicleService.getById(savedVehicle.getId()));
    }
}
