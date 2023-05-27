package com.vacika.projectstartertemplate.service.base;

import com.vacika.projectstartertemplate.domain.base.BaseEntity;
import com.vacika.projectstartertemplate.domain.base.Status;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.log4j.Log4j2;
import org.redisson.api.RedissonClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Log4j2
public abstract class AbstractCrudServiceImpl<ENTITY extends BaseEntity, REPO extends JpaRepository<ENTITY, Long>> implements AbstractCrudService<ENTITY> {

    protected final REPO repository;
    protected final RedissonClient redissonClient;
    protected final String cacheGroup;

    protected AbstractCrudServiceImpl(final REPO repository, final RedissonClient redissonClient, final String cacheGroupName) {
        this.repository = repository;
        this.redissonClient = redissonClient;
        this.cacheGroup = cacheGroupName;
    }

    protected abstract void updateEntityFields(final ENTITY oldEntity, final ENTITY newEntity);

    public List<ENTITY> getAll() {
        final List<ENTITY> response = repository.findAll();
//        RMap<Long, ENTITY> map = redissonClient.getMap(cacheGroup);
//        map.clear();
//        map.clearExpire();
//        map.putAllAsync(response.stream().collect(Collectors.toMap(ENTITY::getId, Function.identity())));
        return response;
    }

    public ENTITY getById(final Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Entity with ID [%d] not found", id)));
//        RMap<Long, ENTITY> map = redissonClient.getMap(cacheGroup);
//        var cachedValue = map.getOrDefault(id, null);
//        return cachedValue != null ? cachedValue : repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Entity with ID [%d] not found", id)));
    }

    @Transactional
    public ENTITY create(final ENTITY entity) {
        log.info(String.format("Creating new entity of type [%s]", entity.getClass().getName()));
        var result = repository.save(entity);
        addToCache(entity.id, result);
        return result;
    }

    @Transactional
    public ENTITY update(final Long id, final ENTITY entityWithNewProperties) {
        log.info(String.format("Updating new entity of type [%s], ID: [%d]", entityWithNewProperties.getClass().getName(), id));
        ENTITY oldEntity = repository.findById(id).orElseThrow(() -> new EntityNotFoundException(String.format("Entity with ID [%d] not found", id)));
        if (oldEntity.equals(entityWithNewProperties)) {
            log.info("The entities are equal, no need to execute update statement..");
            return oldEntity;
        }
        updateEntityFields(oldEntity, entityWithNewProperties);
        var result = repository.save(oldEntity);
        addToCache(id, result);
        return result;
    }

    @Transactional
    public void softDelete(final Long id) {
        log.info(String.format("Removing Entity with ID: [%d]", id));
        ENTITY entity = getById(id);
        entity.setStatus(Status.DELETED);
        repository.save(entity);
        removeFromCache(id);
    }


    @Transactional
    public void softDelete(final ENTITY entity) {
        log.info(String.format("Removing Entity with ID: [%d]", entity.getId()));
        entity.setStatus(Status.DELETED);
        repository.save(entity);
        removeFromCache(entity.getId());
    }

    @Transactional
    public void softDelete(final List<ENTITY> entities) {
        try {
            log.info(String.format("Removing entities of type: [%s]",
                    entities.stream().findFirst().orElseThrow(NoSuchElementException::new).getClass().getName()));
        } catch (NoSuchElementException e) {
            log.info("No entities to remove...");
        }
        List<ENTITY> updatedEntities = entities
                .stream()
                .map(e -> {
                    e.setStatus(Status.DELETED); // soft-delete
                    return e;
                })
                .toList();
        repository.saveAll(updatedEntities);
        removeFromCache(entities.stream().map(ENTITY::getId).toList());
    }

    private void addToCache(final Long id, final ENTITY result) {
//        RMap<Long, Object> cacheMap = redissonClient.getMap(cacheGroup);
//        cacheMap.fastPutAsync(id, result);
    }

    private void removeFromCache(final Long id) {
//        RMap<Long, ENTITY> cacheMap = redissonClient.getMap(cacheGroup);
//        cacheMap.fastRemoveAsync(id);
    }

    private void removeFromCache(final List<Long> ids) {
//        RMap<Long, ENTITY> cacheMap = redissonClient.getMap(cacheGroup);
//        ids.forEach(cacheMap::fastRemoveAsync);
    }
}