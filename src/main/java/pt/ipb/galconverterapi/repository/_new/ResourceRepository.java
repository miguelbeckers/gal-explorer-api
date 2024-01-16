package pt.ipb.galconverterapi.repository._new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.converter.oldToNew.ResourceConverter;
import pt.ipb.galconverterapi.model._new.Resource;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ResourceRepository {
    @Autowired
    private ResourceConverter resourceConverter;

    private List<Resource> resources = new ArrayList<>();

    public void load() {
        resources = resourceConverter.convert();
    }

    public List<Resource> findAll() {
        return resources;
    }
}
