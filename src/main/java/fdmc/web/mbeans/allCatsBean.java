package fdmc.web.mbeans;

import fdmc.domain.models.view.CatViewModel;
import fdmc.service.CatService;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;
import java.util.stream.Collectors;

@Named
@RequestScoped
public class allCatsBean {

    private List<CatViewModel> cats;

    private CatService catService;
    private ModelMapper modelMapper;

    public allCatsBean() {
    }

    @Inject
    public allCatsBean(CatService catService,
                       ModelMapper modelMapper) {
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.cats = this.catService.getAllCats().stream()
                .map(cat -> this.modelMapper.map(cat, CatViewModel.class))
                .collect(Collectors.toList());
    }

    public List<CatViewModel> getCats() {
        return cats;
    }

    public void setCats(List<CatViewModel> cats) {
        this.cats = cats;
    }
}
