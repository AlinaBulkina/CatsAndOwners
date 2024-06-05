package services;

import dto.CatDto;
import dto.OwnerDto;
import entity.Cat;
import exceptions.CatServiceException;
import exceptions.OwnerServiceException;
import lombok.experimental.ExtensionMethod;
import mappers.CatMapper;
import mappers.OwnerMapper;
import entity.Owner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import repositories.ICatRepository;
import repositories.IOwnerRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@ExtensionMethod({OwnerMapper.class, CatMapper.class})
public class OwnerService implements IOwnerService {

    private final IOwnerRepository ownerRepository;
    private final ICatRepository catRepository;

    @Autowired
    public OwnerService(IOwnerRepository ownerRepository, ICatRepository catRepository) {
        this.ownerRepository = ownerRepository;
        this.catRepository = catRepository;
    }

    @Override
    public OwnerDto createOwner(String name, LocalDate birthDate) {
        try {
            Owner owner = new Owner(name, birthDate, new ArrayList<>());
            return ownerRepository.save(owner).asDto();
        } catch (NullPointerException e) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public OwnerDto getOwnerById(int id) {
        return ownerRepository.findById(id).orElseThrow(() -> CatServiceException.noSuchOwner(id)).asDto();
    }

    public void addCat(int ownerId, int catId) throws ChangeSetPersister.NotFoundException {
        var owner = getOwnerById(ownerId);
        var cat = catRepository.findById(catId);

        if (cat.isEmpty()) {
            throw new IllegalArgumentException();
        }

        Cat catEntity = cat.get();

        if (owner.getCats().stream().anyMatch(c -> c.getId() == catId)) {
            throw new IllegalStateException("Cat with id: " + catId + " already belongs to owner");
        }

        owner.getCats().add(catEntity.asDto());
    }

    @Override
    public List<CatDto> findAllCats(int id) {
        Owner owner = ownerRepository.findById(id).orElseThrow(() -> OwnerServiceException.noSuchOwner(id));
        List<CatDto> catDtos = new ArrayList<>();
        for (Cat cat : owner.getCatList()) {
            catDtos.add(CatMapper.asDto(cat));
        }
        return catDtos;
    }

    @Override
    public List<OwnerDto> findAllOwners() {
        return ownerRepository.findAll().stream().map(OwnerMapper::asDto).toList();
    }

    @Override
    public void removeOwner(int id) {
        ownerRepository.deleteById(id);
    }
}
