package pt.ipb.galconverterapi.repository._new;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import pt.ipb.galconverterapi.converter.oldToNew.TimeslotConverter;
import pt.ipb.galconverterapi.model._new.Timeslot;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TimeslotRepository {
    @Autowired
    private TimeslotConverter timeslotConverter;

    private List<Timeslot> timeslots = new ArrayList<>();

    public void load() {
        timeslots = timeslotConverter.convert();
    }

    public List<Timeslot> findAll() {
        return timeslots;
    }
}
