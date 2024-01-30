package avers66.servicemain.controller;

import avers66.servicemain.model.EntityModel;
import avers66.servicemain.model.EntityRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * EntityController
 *
 * @Author Tretyakov Alexandr
 */

@Slf4j
@RestController
@RequestMapping("/api/v1/entity")
public class EntityController {

    @GetMapping
    public ResponseEntity<List<EntityModel>> findAll() {

        List<EntityModel> modelList = new ArrayList<>();
        for (int i = 1; i < 11; i++) modelList.add(EntityModel.createMockModel("Model " + i));
        return ResponseEntity.ok(modelList);
    }

    @GetMapping("/{name}")
    public ResponseEntity<EntityModel> findByName(@PathVariable String name) {
        return ResponseEntity.ok(EntityModel.createMockModel(name));
    }

    @PostMapping
    public ResponseEntity<EntityModel> createModel(EntityRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(EntityModel.createMockModel(request.getName()));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EntityModel> updateById(@PathVariable UUID id, @RequestBody EntityRequest request) {
        return ResponseEntity.ok(new EntityModel(id, request.getName(), Instant.now()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntity(@PathVariable UUID id) {
        log.info("Delete entity by id: {}", id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
