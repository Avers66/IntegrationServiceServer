package avers66.servicemain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.UUID;

/**
 * EntityModel
 *
 * @Author Tretyakov Alexandr
 */


@Data
@AllArgsConstructor
@NoArgsConstructor
public class EntityModel {

    private UUID id;
    private String name;
    private Instant date;

    public static EntityModel createMockModel(String name) {
        return new EntityModel(UUID.randomUUID(), name, Instant.now());

    }

}
