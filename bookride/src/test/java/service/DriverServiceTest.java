package service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.bookride.dto.DriverDto;
import com.bookride.mapper.DriverMapper;
import com.bookride.model.Driver;
import com.bookride.model.Enum.Status;
import com.bookride.repository.DriverRepository;
import com.bookride.service.DriverService;

public class DriverServiceTest {
    @InjectMocks
    private DriverService driverService;

    @Mock
    private DriverRepository driverRepository;

    @Mock
    private DriverMapper driverMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateDriver() {
        Driver driver = new Driver(1L, "John", "Doe", Status.AVAILABLE, LocalTime.parse("08:00:00"),
                LocalTime.parse("18:00:00"), null);
        DriverDto driverDto = new DriverDto(1L, "John", "Doe", Status.AVAILABLE, LocalTime.parse("08:00:00"),
                LocalTime.parse("18:00:00"), null);

        when(driverMapper.toEntity(driverDto)).thenReturn(driver);
        when(driverRepository.save(driver)).thenReturn(driver);
        when(driverMapper.toDto(driver)).thenReturn(driverDto);

        DriverDto result = driverService.create(driver);

        assertNotNull(result);
        assertEquals("John", result.getFirstName());
        verify(driverRepository, times(1)).save(driver);
    }

    @Test
    void testGetDriverById() {
        Driver driver = new Driver(1L, "John", "Doe", Status.AVAILABLE, LocalTime.parse("08:00:00"),
                LocalTime.parse("18:00:00"), null);
        when(driverRepository.findById(1L)).thenReturn(Optional.of(driver));
        when(driverMapper.toDto(driver)).thenReturn(new DriverDto(1L, "John", "Doe", Status.AVAILABLE,
                LocalTime.parse("08:00:00"), LocalTime.parse("18:00:00"), null));

        DriverDto result = driverService.getById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(driverRepository, times(1)).findById(1L);
    }
}
