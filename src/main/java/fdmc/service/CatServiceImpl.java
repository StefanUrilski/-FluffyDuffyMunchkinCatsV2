package fdmc.service;

import fdmc.domain.entities.Cat;
import fdmc.domain.models.service.CatServiceModel;
import fdmc.repository.CatRepository;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

public class CatServiceImpl implements CatService {

    private final ModelMapper modelMapper;
    private final CatRepository catRepository;

    @Inject
    public CatServiceImpl(ModelMapper modelMapper,
                          CatRepository catRepository) {
        this.modelMapper = modelMapper;
        this.catRepository = catRepository;
    }

    @Override
    public boolean saveCat(CatServiceModel catServiceModel) {
        try {
            catRepository.save(modelMapper.map(catServiceModel, Cat.class));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public List<CatServiceModel> getAllCats() {
        return catRepository.findAll().stream()
                .map(cat -> modelMapper.map(cat, CatServiceModel.class))
                .collect(Collectors.toList());
    }
}
