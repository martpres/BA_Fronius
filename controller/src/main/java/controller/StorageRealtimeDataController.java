package controller;

import dto.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pagination.PaginationUtil;
import response.QueryDslResponse;
import service.StorageRealtimeDataService;

import java.time.ZonedDateTime;
import java.util.Optional;

@RestController
@RequestMapping("/api/storage-realtime-data")
@Transactional(readOnly = true)
public class StorageRealtimeDataController {
    private final StorageRealtimeDataService storageRealtimeDataService;

    public StorageRealtimeDataController(StorageRealtimeDataService storageRealtimeDataService) {
        this.storageRealtimeDataService = storageRealtimeDataService;
    }

    @GetMapping(value = "/state-of-charge-storage", produces = "application/json")
    public QueryDslResponse<ResponseStateOfChargeStorageDto> loadStateOfChargeStorage(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                                                      @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                                                      @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                                                      @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize) {
        return storageRealtimeDataService.loadStateOfChargeStorage(startDate, endDate, PaginationUtil.getPagination(page, pagesize));
    }

    @GetMapping(value = "/capacity-storage", produces = "application/json")
    public QueryDslResponse<ResponseCapacityStorageDto> loadCapacityStorage(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                                                   @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                                                   @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                                                   @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize) {
        return storageRealtimeDataService.loadCapacityStorage(startDate, endDate, PaginationUtil.getPagination(page, pagesize));
    }

    @GetMapping(value = "/manufacturer-storage", produces = "application/json")
    public QueryDslResponse<ResponseManufacturerStorageDto> loadManufacturerStorage(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                                                           @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                                                           @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                                                           @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize) {
        return storageRealtimeDataService.loadManufacturerStorage(startDate, endDate, PaginationUtil.getPagination(page, pagesize));
    }

    @GetMapping(value = "/model-storage", produces = "application/json")
    public QueryDslResponse<ResponseModelStorageDto> loadModelStorage(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                                                    @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                                                    @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                                                    @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize) {
        return storageRealtimeDataService.loadModelStorage(startDate, endDate, PaginationUtil.getPagination(page, pagesize));
    }

    @GetMapping(value = "/serial-storage", produces = "application/json")
    public QueryDslResponse<ResponseSerialStorageDto> loadSerialStorage(@RequestParam(value = "startDate", required = false) Optional<ZonedDateTime> startDate,
                                                                                    @RequestParam(value = "endDate", required = false) Optional<ZonedDateTime> endDate,
                                                                                    @RequestParam(value = "page", required = false) Optional<Integer> page,
                                                                                    @RequestParam(value = "pagesize", required = false) Optional<Integer> pagesize) {
        return storageRealtimeDataService.loadSerialStorage(startDate, endDate, PaginationUtil.getPagination(page, pagesize));
    }


}
