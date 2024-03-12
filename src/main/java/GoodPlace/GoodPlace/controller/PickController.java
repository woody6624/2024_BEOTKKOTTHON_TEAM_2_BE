package GoodPlace.GoodPlace.controller;
import GoodPlace.GoodPlace.service.LocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PickController {

    private final LocationService locationService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String getRest(){
        return locationService.getRestaurant();
    }
}
