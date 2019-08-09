package fdmc.config;

import fdmc.util.ValidationUtil;
import fdmc.util.ValidationUtilImpl;
import org.modelmapper.ModelMapper;

import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class ApplicationBeenConfiguration {
    private static final String PERSISTENCE_UNIT_NAME = "fluffy_duffy_munchkin_cats_v2";

    @Produces
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Produces
    public EntityManager entityManager(){
        return Persistence
                .createEntityManagerFactory(PERSISTENCE_UNIT_NAME)
                .createEntityManager();
    }
}
