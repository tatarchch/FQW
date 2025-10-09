package com.example.fqw.service;

import com.example.fqw.dto.PetServiceDto;
import com.example.fqw.entity.PetService;
import com.example.fqw.repositories.PetServiceRepository;
import com.example.fqw.services.PetServiceService;
import com.example.fqw.configuration.RedisTestConfiguration;
import com.example.fqw.configuration.TestContainersConfiguration;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.data.redis.core.RedisTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = {TestContainersConfiguration.class, RedisTestConfiguration.class})
@Slf4j
public class PetServiceServiceCacheTest {

    @Autowired
    @SpyBean
    PetServiceRepository repository;

    @Autowired
    PetServiceService service;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @BeforeEach
    void prepare() {
        redisTemplate.delete(redisTemplate.keys("service*"));
    }

    @Test
    void getServiceByIdCacheTest() {
        PetService petService = this.createPetService(null, "name", null, 100, null);
        log.info("Created service");

        var id = petService.getId();

        String cacheKey = "service::" + id;
        assertNull(redisTemplate.opsForValue().get(cacheKey),
                "Cache should be empty");

        long start1 = System.currentTimeMillis();
        var petServiceFromDb = service.getServiceById(id);
        long dbTime = System.currentTimeMillis() - start1;
        log.info("Service from db: {}", petServiceFromDb);
        log.info("DB call took: {}ms", dbTime);

        Object cachedPetServiceDto = redisTemplate.opsForValue().get(cacheKey);
        assertNotNull(cachedPetServiceDto, "Data should be cached in Redis after first call");

        log.info("Cached service type: {}", cachedPetServiceDto.getClass().getName());
        log.info("Cached service: {}", cachedPetServiceDto);

        assertInstanceOf(PetServiceDto.class, cachedPetServiceDto);
        assertEquals(petServiceFromDb.getName(), ((PetServiceDto) cachedPetServiceDto).getName());

        long start2 = System.currentTimeMillis();
        var petServiceDtoFromCache = service.getServiceById(id);
        long cacheTime = System.currentTimeMillis() - start2;
        log.info("Cache call took: {}ms", cacheTime);

        assertTrue(cacheTime < dbTime,
                "Cache should be faster. db: " + dbTime + "ms, Cache: " + cacheTime + "ms");

        verify(repository, times(1)).findById(id);

        assertEquals(petServiceFromDb.getId(), petServiceDtoFromCache.getId());
        assertEquals(petServiceFromDb.getName(), petServiceDtoFromCache.getName());

    }

    @Test
    void getPetServiceByIdCacheTestForAnyMaster() {
        PetService petService = this.createPetService(null, "test", null, 100, null);
        log.info("Created service");

        var id = petService.getId();

        service.getServiceById(id);
        service.getServiceById(id);
        service.getServiceById(id);
        service.getServiceById(id);

        verify(repository, times(1)).findById(id);
    }

    @Test
    void deletePetServiceByIdCacheTest() {
        PetService petService = this.createPetService(null, "name", null, 100, null);
        log.info("Added new service to db");

        var id = petService.getId();

        PetServiceDto savedPetService = service.getServiceById(id);
        log.info("Added new service to cache");

        String idCacheKey = "service::" + savedPetService.getId();
        Object cachedById = redisTemplate.opsForValue().get(idCacheKey);
        assertNotNull(cachedById, "Should be cached by id");

        service.deleteServiceById(id);

        Object deletedCacheById = redisTemplate.opsForValue().get(idCacheKey);

        assertNull(deletedCacheById, "Cached by id should be cleared and equal null");

    }

    private PetService createPetService(Long id,
                                        String name,
                                        Integer level,
                                        Integer cost,
                                        Boolean active) {
        var service = new PetService();
        service.setId(id);
        service.setName(name);
        service.setLevel(level);
        service.setCost(cost);
        service.setActive(active);

        return repository.save(service);
    }

}
