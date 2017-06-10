package de.whisdol.greencity.api;

import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.SpotMaintainActionDAO;
import de.whisdol.greencity.model.SpotMaintainAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by cedric on 28.05.17.
 */
@RestController
@RequestMapping("/spots/{spotId}/actions")
public class SpotMaintainActionRestController {
    private final SpotMaintainActionDAO actionDao;

    @Autowired
    public SpotMaintainActionRestController() {
        this.actionDao = (SpotMaintainActionDAO) GreencityserverApplication.context.getBean("spotMaintainActionDAO");
    }

    // Get all actions for a defined spot
    @GetMapping(value = { "", "/" }, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> getActionsBySpotId(@PathVariable long spotId) {
        List<SpotMaintainAction> actions;
        try {
            actions = actionDao.getAllSpotMaintainActionsBySpot(spotId);
        } catch (ObjectNotFoundException e) {
            // The spot has not been found
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // The request is invalid and caused an error
            return ResponseEntity.badRequest().build();
        }

        if (actions.size() < 1) {
            // There are no actions for this spot
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actions);
    }

    @PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> createAction(@PathVariable long spotId, @RequestBody SpotMaintainAction requestAction) {
        requestAction.setSpotId(spotId);
        SpotMaintainAction action;
        try {
            // Return existing action if an action with the unique key (same spot id, timestamp) already exists
            action = actionDao.getActionByAction(requestAction);
        } catch (ObjectNotFoundException e) {
            // If the action does not yet exist, create it
            action = actionDao.createSpotMaintainAction(requestAction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{ \"error\": \"Invalid POST body for a action " +
                    requestAction + "\"}");
        }
        return ResponseEntity.ok(action);
    }

    // Update existing action
    @PutMapping(value = "/{actionId}", produces = { MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> updateAction(@PathVariable long actionId, @RequestBody SpotMaintainAction action) {
        try {
            actionDao.updateSpotMaintainAction(actionId, action);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    // Get single action
    @GetMapping(value = "/{actionId}", produces = { MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> getAction(@PathVariable long actionId, @PathVariable long spotId) {
        SpotMaintainAction action;
        try {
            action = actionDao.selectSpotMaintainActionById(actionId);
        } catch (ObjectNotFoundException e) {
            // Action has not been found
            return ResponseEntity.notFound().build();
        }

        if (action.getSpotId() != spotId) {
            // Action with this ID exists, but it does not belong to the requested spot
            return ResponseEntity.badRequest().body("SpotMaintainAction with ID " + actionId +
                    " does not belong to Spot with ID " + spotId);
        }

        return ResponseEntity.ok(action);

    }

    // Delete action
    @DeleteMapping(value = "/{actionId}", produces = { MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> deleteAction(@PathVariable long actionId) {
        actionDao.deleteSpotMaintainAction(actionId);
        return ResponseEntity.ok().build();
    }


}
