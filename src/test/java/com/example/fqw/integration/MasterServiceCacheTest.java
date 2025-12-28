package com.example.fqw.integration;

import com.example.fqw.dto.MasterDto;
import com.example.fqw.entity.Master;
import com.example.fqw.repositories.MasterRepository;
import com.example.fqw.services.MasterService;
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
class MasterServiceCacheTest {

    @Autowired
    @SpyBean
    MasterRepository repository;

    @Autowired
    MasterService service;

    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @BeforeEach
    void prepare() {
        redisTemplate.delete(redisTemplate.keys("master*"));
    }


    @Test
    void getMasterByIdCacheTest() {
        Master master = this.createMaster(null, "test1", 1, null);
        log.info("Created master");

        var id = master.getId();

        String cacheKey = "master::" + id;
        assertNull(redisTemplate.opsForValue().get(cacheKey),
                "Cache should be empty");

        long start1 = System.currentTimeMillis();
        var masterDtoFromDb = service.getMasterById(id);
        long dbTime = System.currentTimeMillis() - start1;
        log.info("Master from db: {}", masterDtoFromDb);
        log.info("DB call took: {}ms", dbTime);

        Object cachedMasterDto = redisTemplate.opsForValue().get(cacheKey);
        assertNotNull(cachedMasterDto, "Data should be cached in Redis after first call");

        log.info("Cached master type: {}", cachedMasterDto.getClass().getName());
        log.info("Cached master: {}", cachedMasterDto);

        assertInstanceOf(MasterDto.class, cachedMasterDto);
        assertEquals(masterDtoFromDb.getName(), ((MasterDto) cachedMasterDto).getName());

        long start2 = System.currentTimeMillis();
        var masterDtoFromCache = service.getMasterById(id);
        long cacheTime = System.currentTimeMillis() - start2;
        log.info("Cache call took: {}ms", cacheTime);

        assertTrue(cacheTime < dbTime,
                "Cache should be faster. db: " + dbTime + "ms, Cache: " + cacheTime + "ms");

        verify(repository, times(1)).findById(id);

        assertEquals(masterDtoFromDb.getId(), masterDtoFromCache.getId());
        assertEquals(masterDtoFromDb.getName(), masterDtoFromCache.getName());

    }

    @Test
    void getMasterByIdCacheTestForAnyMaster() {
        Master master = this.createMaster(null, "test2", 1, null);
        log.info("Created master");

        var id = master.getId();

        service.getMasterById(id);
        service.getMasterById(id);
        service.getMasterById(id);
        service.getMasterById(id);

        verify(repository, times(1)).findById(id);
    }


    @Test
    void getMasterByNameCacheTest() {
        var masterName = "Test Master";

        String cacheKey = "master::" + masterName;
        assertNull(redisTemplate.opsForValue().get(cacheKey),
                "Cache should be empty");

        this.createMaster(null, masterName, 1, null);
        log.info("Created master");

        long start1 = System.currentTimeMillis();
        var masterDtoFromDb = service.getMasterByName(masterName);
        long dbTime = System.currentTimeMillis() - start1;
        log.info("Master from db: {}", masterDtoFromDb);
        log.info("DB call took: {}ms", dbTime);

        Object cachedMasterDto = redisTemplate.opsForValue().get(cacheKey);
        assertNotNull(cachedMasterDto, "Data should be cached in Redis after first call");

        log.info("Cached master type: {}", cachedMasterDto.getClass().getName());
        log.info("Cached master: {}", cachedMasterDto);

        assertInstanceOf(MasterDto.class, cachedMasterDto);
        assertEquals(masterDtoFromDb.getName(), ((MasterDto) cachedMasterDto).getName());

        long start2 = System.currentTimeMillis();
        var masterDtoFromCache = service.getMasterByName(masterName);
        long cacheTime = System.currentTimeMillis() - start2;
        log.info("Cache call took: {}ms", cacheTime);

        assertTrue(cacheTime < dbTime,
                "Cache should be faster. db: " + dbTime + "ms, Cache: " + cacheTime + "ms");

        verify(repository, times(1)).findMasterByName(masterName);

        assertEquals(masterDtoFromDb.getId(), masterDtoFromCache.getId());
        assertEquals(masterDtoFromDb.getName(), masterDtoFromCache.getName());
    }

    @Test
    void getMasterByNameCacheTestForAnyMaster() {
        var masterName = "test Name";

        this.createMaster(1L, masterName, 1, null);
        log.info("Created master");

        service.getMasterByName(masterName);
        service.getMasterByName(masterName);
        service.getMasterByName(masterName);
        service.getMasterByName(masterName);

        verify(repository, times(1)).findMasterByName(masterName);
    }


    @Test
    void addNewMasterCacheTest() {
        MasterDto masterDto = this.createMasterDto(null, "test3", 1);

        MasterDto savedMaster = service.addNewMaster(masterDto);
        log.info("Added new master");
        assertNotNull(savedMaster.getId());


        String idCacheKey = "master::" + savedMaster.getId();
        Object cachedById = redisTemplate.opsForValue().get(idCacheKey);
        log.info("Master from id cache {}", cachedById);

        assertNotNull(cachedById, "Should be cached by id");
        assertInstanceOf(MasterDto.class, cachedById);
        assertEquals(savedMaster.getName(), ((MasterDto) cachedById).getName());


        String nameCacheKey = "master::" + savedMaster.getName();
        Object cachedByName = redisTemplate.opsForValue().get(nameCacheKey);
        log.info("Master from name cache {}", cachedByName);

        assertNotNull(cachedByName, "Should be cached by name");
        assertInstanceOf(MasterDto.class, cachedByName);
        assertEquals(savedMaster.getId(), ((MasterDto) cachedByName).getId());


        MasterDto byId = service.getMasterById(savedMaster.getId());
        MasterDto byName = service.getMasterByName(savedMaster.getName());

        verify(repository, times(0)).findById(savedMaster.getId());
        verify(repository, times(0)).findMasterByName(savedMaster.getName());
    }

    @Test
    void inactivateMasterByIdCacheTest() {

        MasterDto masterDto = this.createMasterDto(null, "test4", 1);

        MasterDto savedMaster = service.addNewMaster(masterDto);
        log.info("Added new master to bd and cache");

        String idCacheKey = "master::" + savedMaster.getId();
        Object cachedById = redisTemplate.opsForValue().get(idCacheKey);
        assertNotNull(cachedById, "Should be cached by id");

        String nameCacheKey = "master::" + savedMaster.getName();
        Object cachedByName = redisTemplate.opsForValue().get(nameCacheKey);
        assertNotNull(cachedByName, "Should be cached by name");

        MasterDto inactiveMaster = service.inactivateMasterById(savedMaster.getId());

        assertEquals(savedMaster.getId(), inactiveMaster.getId());
        assertEquals(savedMaster.getName(), inactiveMaster.getName());
        assertEquals(savedMaster.getLevel(), inactiveMaster.getLevel());


        String inactiveIdCacheKey = "master::" + inactiveMaster.getId();
        Object inactiveCachedById = redisTemplate.opsForValue().get(inactiveIdCacheKey);

        String inactiveNameCacheKey = "master::" + inactiveMaster.getName();
        Object inactiveCachedByName = redisTemplate.opsForValue().get(inactiveNameCacheKey);

        assertEquals(idCacheKey, inactiveIdCacheKey);
        assertEquals(nameCacheKey, inactiveNameCacheKey);

        assertNull(inactiveCachedById, "Cached by id should be cleared and equal null");
        assertNull(inactiveCachedByName, "Cached by name should be cleared and equal null");

    }


    private Master createMaster(Long id,
                                String name,
                                Integer level,
                                Boolean active) {
        var master = new Master();
        master.setId(id);
        master.setName(name);
        master.setLevel(level);
        master.setActive(active);

        return repository.save(master);
    }

    private MasterDto createMasterDto(Long id,
                                      String name,
                                      Integer level) {
        var masterDto = new MasterDto();
        masterDto.setId(id);
        masterDto.setName(name);
        masterDto.setLevel(level);

        return masterDto;
    }

}
