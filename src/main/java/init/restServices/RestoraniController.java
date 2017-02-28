package init.restServices;

import init.modelFromDB.RestoranEntity;
import init.repositories.RestoranRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by janva on 2/27/2017.
 */
@RestController
@RequestMapping("/restorani")
public class RestoraniController {
    @Autowired
    private RestoranRepository restoranRepository;

    @RequestMapping(path="/getAll", method = RequestMethod.GET)
    public List<RestoranEntity> getAll(){
        return restoranRepository.getSviRestorani();
    }
}
