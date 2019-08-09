package fdmc.web.mbeans;

import fdmc.domain.models.binding.CatCreateBindingModel;
import fdmc.domain.models.service.CatServiceModel;
import fdmc.service.CatService;
import fdmc.util.ValidationUtil;
import fdmc.util.ValidationUtilImpl;
import org.modelmapper.ModelMapper;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.time.ZoneId;

@Named(value = "createForm")
@RequestScoped
public class CatCreateBean {

    private CatCreateBindingModel createCat;

    private CatService catService;
    private ModelMapper modelMapper;
    private ValidationUtil validationUtil;

    public CatCreateBean() {
    }

    @Inject
    public CatCreateBean(CatService catService,
                         ModelMapper modelMapper) {
        this.createCat = new CatCreateBindingModel();
        this.catService = catService;
        this.modelMapper = modelMapper;
        this.validationUtil = new ValidationUtilImpl();
    }

    public CatCreateBindingModel getCreateCat() {
        return createCat;
    }

    public void setCreateCat(CatCreateBindingModel createCat) {
        this.createCat = createCat;
    }

    public void registerCat() throws IOException {
        CatServiceModel cat = modelMapper.map(createCat, CatServiceModel.class);
        cat.setAddedOn(createCat.getAddedOn().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

        if (! validationUtil.isValid(cat)) {
            return;
        }

        catService.saveCat(modelMapper.map(cat, CatServiceModel.class));

        FacesContext.getCurrentInstance().getExternalContext().redirect("/");
    }
}
