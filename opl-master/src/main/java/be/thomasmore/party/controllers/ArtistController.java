package be.thomasmore.party.controllers;

import be.thomasmore.party.model.Artist;


import be.thomasmore.party.repositories.ArtistRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;
@Controller
public class ArtistController {
    @Autowired
    private ArtistRepository artistRepository;

    @GetMapping("/artistlist")
    public String artistlist(Model model) {

        final Iterable<Artist> allArtists = artistRepository.findAll();
        model.addAttribute("artists", allArtists);

        return "artistlist";
    }

    @GetMapping({"/artistdetails/{id}", "/artistdetails"})
    public String artistdetails(Model model, @PathVariable(required = false) Integer id) {
        if (id == null) return "artistdetails";
        //Venue venue = new Venue("Boesj", "https://www.facebook.com/boesjkammeree/", 100, false, true, false, false, "Mechelen", 1);
        Optional<Artist> artistFromDb = artistRepository.findById(id);
        //noinspection OptionalIsPresent

        if (artistFromDb.isPresent()) {
            model.addAttribute("artist", artistFromDb.get());
        }
        return "artistdetails";
    }
}

