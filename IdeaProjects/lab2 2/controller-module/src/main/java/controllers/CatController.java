package controllers;

import dto.CatDto;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import services.CatService;

import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/cat/")
public class CatController implements ICatController {

    private final CatService serviceRepository;

    @Autowired
    public CatController(CatService serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    @Override
    @PostMapping()
    public CatDto createCat(@Valid @RequestBody CatDto catDto) {
        return serviceRepository.createCat(
                catDto.name(),
                catDto.birthDate(),
                catDto.breed(),
                catDto.color(),
                catDto.ownerId());
    }

    @PostMapping("create")
    public ResponseEntity<CatDto> createCat(@RequestParam(name = "name") String name,
                            @RequestParam(name = "date") LocalDate date,
                            @RequestParam(name = "breed") String breedName,
                            @RequestParam(name = "color") String color,
                            @RequestParam(name = "ownerId") int ownerId) {
        try{
            return ResponseEntity.ok(serviceRepository.createCat(name, date, breedName, color, ownerId));
        } catch (ConstraintViolationException e){
            System.out.println(e.getMessage());

            return ResponseEntity.internalServerError().build();
        }
    }


    @Override
    @GetMapping("{id}")
    public ResponseEntity<CatDto> getCatById(@PathVariable("id") int id) {
        if (checkAdminAuth() || checkUserAuth(id)) {
            return new ResponseEntity<>(serviceRepository.getCatById(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    @GetMapping("friends/{id}")
    public ResponseEntity<List<CatDto>> findAllFriends(@PathVariable("id") int id) {
        if (checkAdminAuth() || checkUserAuth(id)) {
            return new ResponseEntity<>(serviceRepository.findAllFriends(id), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }

    @Override
    @GetMapping("getAll")
    public List<CatDto> findAllCats() {
        if (checkAdminAuth()) {
            return serviceRepository.findAllCats();
        }
        return serviceRepository.findAllOwnersCats();
    }

    @Override
    @DeleteMapping("{id}")
    public HttpStatus removeCat(@PathVariable("id") int id) {
        if (checkAdminAuth() || checkUserAuth(id)) {
            serviceRepository.removeCat(id);
            return HttpStatus.OK;
        }
        return HttpStatus.FORBIDDEN;
    }

    @Override
    @GetMapping("getBreed")
    public List<CatDto> findCatByBreed(@RequestParam(name = "breed") String breed) {
        if (checkAdminAuth()) {
            return serviceRepository.findAllCatsByBreed(breed);
        }
        return serviceRepository.findOwnerCatsByBreed(breed);
    }

    @Override
    @GetMapping("getColor")
    public List<CatDto> findCatByColor(@RequestParam(name = "color") String color) {
        if (checkAdminAuth()) {
            return serviceRepository.findAllCatsByColor(color);
        }
        return serviceRepository.findOwnerCatsByColor(color);
    }

    @Override
    @PutMapping("makeFriends")
    public void makeFriends(@RequestParam(name = "cat1") int catId1,
                            @RequestParam(name = "cat2") int catId2) {
        serviceRepository.makeFriends(catId1, catId2);
    }

    @Override
    @PutMapping("unfriend")
    public void unfriendCats(@RequestParam(name = "cat1") int catId1,
                             @RequestParam(name = "cat2") int catId2) {
        serviceRepository.unfriendCats(catId1, catId2);
    }

    private boolean checkAdminAuth(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"));
    }

    private boolean checkUserAuth(int id) {
        return serviceRepository.checkOwner(id);
    }
}
