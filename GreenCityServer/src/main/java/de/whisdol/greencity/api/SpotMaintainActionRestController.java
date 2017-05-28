package de.whisdol.greencity.api;

import com.sun.org.apache.regexp.internal.RE;
import de.whisdol.greencity.GreencityserverApplication;
import de.whisdol.greencity.dao.SpotMaintainActionDAO;
import de.whisdol.greencity.model.SpotMaintainAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
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

    @GetMapping(value = { "", "/" }, produces = {MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> getActionsBySpotId(@PathVariable long spotId) {
        List<SpotMaintainAction> actions;
        try {
            actions = actionDao.getAllSpotMaintainActionsBySpot(spotId);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }

        if (actions.size() < 1) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(actions);
    }

    @PostMapping(value = "/create", produces = { MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> createAction(@PathVariable long spotId, @RequestBody SpotMaintainAction requestAction) {
        requestAction.setSpotId(spotId);
        SpotMaintainAction action;
        try {
            action = actionDao.getActionByAction(requestAction);
        } catch (ObjectNotFoundException e) {
            action = actionDao.createSpotMaintainAction(requestAction);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("{ \"error\": \"Invalid POST body for a action " + requestAction + "\"}");
        }
        return ResponseEntity.ok(action);
    }

    @PutMapping(value = "/{actionId}", produces = { MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> updateAction(@PathVariable long actionId, @RequestBody SpotMaintainAction action) {
        try {
            actionDao.updateSpotMaintainAction(actionId, action);
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{actionId}", produces = { MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> getAction(@PathVariable long actionId, @PathVariable long spotId) {
        SpotMaintainAction action;
        try {
            action = actionDao.selectSpotMaintainActionById(actionId);
        } catch (ObjectNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

        if (action.getSpotId() != spotId) {
            return ResponseEntity.badRequest().body("SpotMaintainAction with ID " + actionId + " does not belong to Spot with ID " + spotId);
        }

        return ResponseEntity.ok(action);

    }

    @DeleteMapping(value = "/{actionId}", produces = { MediaType.APPLICATION_JSON_VALUE})
    ResponseEntity<?> deleteAction(@PathVariable long actionId) {
        actionDao.deleteSpotMaintainAction(actionId);
        return ResponseEntity.ok().build();
    }


}
