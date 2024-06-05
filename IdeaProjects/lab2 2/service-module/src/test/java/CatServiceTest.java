import exceptions.CatServiceException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import repositories.ICatRepository;
import repositories.IOwnerRepository;
import services.CatService;

import java.time.LocalDate;

import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CatServiceTest {

    @Mock
    private ICatRepository catRepository;

    @Mock
    private IOwnerRepository ownerRepository;

    @InjectMocks
    private CatService catService;

    @Test
    public void EmptyName() {
        when(ownerRepository.existsById(anyInt())).thenReturn(true);

        assertThrows(IllegalArgumentException.class, () -> catService.createCat("",
                LocalDate.of(2008, 1, 1),
                "Sphynx",
                "Black",
                1));
    }

    @Test
    public void add_CatWithEmptyBreed_ShouldThrowIllegalArgumentException() {
        assertThrows(CatServiceException.class, () -> catService.createCat("",
                LocalDate.of(2008, 1, 1),
                "2",
                "Black",
                1));
    }

    @Test
    public void add_FriendWithSameIdThatHasCat_ShouldThrowCircularReferenceException() {
        assertThrows(CatServiceException.class, () -> catService.makeFriends(1, 1));
    }

    @Test
    public void get_CatWithNegativeId_ShouldReturnIllegalArgumentException() {
        assertThrows(CatServiceException.class, () -> catService.getCatById(-1));
    }


    @Test
    public void FutureBirthday() {
        assertThrows(CatServiceException.class, () -> catService.createCat("",
                LocalDate.of(2118, 1, 1),
                "Sphynx",
                "Black",
                1));
    }
}