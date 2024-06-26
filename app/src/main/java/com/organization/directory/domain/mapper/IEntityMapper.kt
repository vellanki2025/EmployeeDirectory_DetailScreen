package com.organization.directory.domain.mapper

/**
 * An interface for mapping entities to models.
 */
interface IEntityMapper<Entity, Model> {

    /**
     * Maps an entity to a model.
     *
     * @param entity The entity to map.
     * @return The model that was mapped from the entity.
     */
    fun mapFromEntity(entity: Entity): Model
}