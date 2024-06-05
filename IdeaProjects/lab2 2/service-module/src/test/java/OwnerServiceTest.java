import dto.OwnerDto;
import entity.Owner;
import exceptions.CatServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import repositories.ICatRepository;
import repositories.IOwnerRepository;
import services.CatService;
import services.OwnerService;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@RunWith(MockitoJUnitRunner.class)
public class OwnerServiceTest {

    @Mock
    private ICatRepository catRepository;

    @Mock
    private IOwnerRepository ownerRepository;

    @InjectMocks
    private OwnerService ownerService;

    @InjectMocks
    private CatService catService;

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void OwnerEmptyName() {
        assertThrows(IllegalArgumentException.class, () ->
                ownerService.createOwner("", LocalDate.of(2000, 1, 1)));
    }

    @Test
    public void InvalidDate() {
        assertThrows(IllegalArgumentException.class, () ->
                ownerService.createOwner("ValidName",
                        LocalDate.of(3456, 1, 1)));
    }

    @Test
    public void NotExitsCat() {
        var owner = new Owner("Lola",
                LocalDate.of(2002, 1, 1), new ArrayList<>());

        assertThrows(CatServiceException.class,
                () -> ownerService.addCat(1, 1));
    }

    @Test
    public void FutureBirthday() {
        var ownerDto = new OwnerDto(1, "man",
                LocalDate.of(2002, 1, 1), new ArrayList<>());


        assertThrows(CatServiceException.class, () -> ownerService.addCat(1, 1));
    }
}
