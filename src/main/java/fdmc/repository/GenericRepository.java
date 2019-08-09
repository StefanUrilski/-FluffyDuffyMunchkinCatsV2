package fdmc.repository;

import java.util.List;

public interface GenericRepository<Entity, ID> {

    void save(Entity entity);

    Entity findById(ID id);
    
    List<Entity> findAll();
    
}
