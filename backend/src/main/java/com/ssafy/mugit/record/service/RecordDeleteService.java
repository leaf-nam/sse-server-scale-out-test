package com.ssafy.mugit.record.service;

import com.ssafy.mugit.mugitory.service.MugitoryService;
import com.ssafy.mugit.record.entity.Record;
import com.ssafy.mugit.record.repository.RecordRepository;
import com.ssafy.mugit.record.repository.RecordSourceRepository;
import com.ssafy.mugit.record.util.ValidateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecordDeleteService {

    private final RecordRepository recordRepository;
    private final RecordSourceRepository recordSourceRepository;
    private final MugitoryService mugitoryService;
    private final ValidateUtil validateUtil;

    public void deleteRecord(Long userId, Long recordId) {
        Record record = validateUtil.validateRecord(userId, recordId);
        recordRepository.delete(record);
        mugitoryService.deleteRecordMugitory(record);
    }
}
