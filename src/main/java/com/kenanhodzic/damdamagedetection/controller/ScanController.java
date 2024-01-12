package com.kenanhodzic.damdamagedetection.controller;

import com.kenanhodzic.damdamagedetection.dto.Scan;
import com.kenanhodzic.damdamagedetection.service.ScanService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping(value = {"${api.v1.prefix}/scan"}, produces = MediaType.APPLICATION_JSON_VALUE)
public class ScanController {
    private final ScanService service;

    public ScanController(final ScanService service){
        this.service = service;
    }

    @GetMapping("/list")
    public ResponseEntity<List<Scan>> getAll() {
        final List<Scan> models = service.getAll();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping("/list/{modelId}")
    public ResponseEntity<List<Scan>> getAllByModelId(@PathVariable Integer modelId){
        final List<Scan> models = service.getAllByModelId(modelId);
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Scan> getById(@PathVariable final Integer id) {
        final Optional<Scan> model = service.getById(id);
        return model.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/create")
    public ResponseEntity<Scan> save(@RequestBody final Scan model) {
        final Scan savedModel = service.save(model);
        return new ResponseEntity<>(savedModel, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Scan> update(@PathVariable final Integer id, @RequestBody final Scan model) {
        final Optional<Scan> updatedModel = service.update(id, model);
        return updatedModel.map(object -> new ResponseEntity<>(object, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable final Integer id) {
        final Optional<Scan> existingModel = service.getById(id);
        if (existingModel.isPresent()) {
            service.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
