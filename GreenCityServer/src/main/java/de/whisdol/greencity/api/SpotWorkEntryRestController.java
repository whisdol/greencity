package de.whisdol.greencity.api;

import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.SpotWorkEntryDAO;
import de.whisdol.greencity.model.SpotWorkEntry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cedric on 28.05.17.
 */
@RestController
@RequestMapping("/spots/{spotId}/workEntries")
public class SpotWorkEntryRestController {
    private final SpotWorkEntryDAO spotWorkEntryDAO;

    @Autowired
    public SpotWorkEntryRestController() {
        this.spotWorkEntryDAO = (SpotWorkEntryDAO) GreencityserverApplication.context.getBean("spotWorkEntryDAO");
    }

    // Get all entries for a defined spot
    @CrossOrigin
    @GetMapping(value = { "", "/" }, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> getWorkEntriesBySpotId(@PathVariable long spotId) {
        List<SpotWorkEntry> entries;
        try {
            entries = spotWorkEntryDAO.getAllSpotWorkEntriesBySpot(spotId);
        } catch (ObjectNotFoundException e) {
            // The spot has not been found
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // The request is invalid and caused an error
            return ResponseEntity.badRequest().build();
        }

        if (entries.size() < 1) {
            // There are no entries for this spot
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(entries);
    }

    @CrossOrigin
    @PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> createWorkEntry(@PathVariable long spotId, @RequestBody SpotWorkEntry requestEntry) {
        requestEntry.setSpotId(spotId);
        SpotWorkEntry entry;
        try {
            // Return existing entry if an entry with the unique key (same spot id, timestamp) already exists
            entry = spotWorkEntryDAO.getEntryByEntry(requestEntry);
        } catch (ObjectNotFoundException e) {
            // If the entry does not yet exist, create it
            entry = spotWorkEntryDAO.createSpotWorkEntry(requestEntry);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{ \"error\": \"Invalid POST body for a entry " +
                    requestEntry + "\"}");
        }
        return ResponseEntity.ok(entry);
    }

    // Update existing workEntry
    @CrossOrigin
    @PutMapping(value = "/{workEntryId}", produces = { MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> updateWorkEntry(@PathVariable long workEntryId, @RequestBody SpotWorkEntry workEntry) {
        try {
            spotWorkEntryDAO.updateSpotWorkEntry(workEntryId, workEntry);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    // Get single entry
    @CrossOrigin
    @GetMapping(value = "/{workEntryId}", produces = { MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> getWorkEntry(@PathVariable long workEntryId, @PathVariable long spotId) {
        SpotWorkEntry entry;
        try {
            entry = spotWorkEntryDAO.selectSpotWorkEntryById(workEntryId);
        } catch (ObjectNotFoundException e) {
            // Entry has not been found
            return ResponseEntity.notFound().build();
        }

        if (entry.getSpotId() != spotId) {
            // Entry with this ID exists, but it does not belong to the requested spot
            return ResponseEntity.badRequest().body("SpotWorkEntry with ID " + workEntryId +
                    " does not belong to Spot with ID " + spotId);
        }

        return ResponseEntity.ok(entry);

    }

    // Delete entry
    @CrossOrigin
    @DeleteMapping(value = "/{workEntryId}", produces = { MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> deleteWorkEntry(@PathVariable long workEntryId) {
        spotWorkEntryDAO.deleteSpotWorkEntry(workEntryId);
        return ResponseEntity.ok().build();
    }


}
