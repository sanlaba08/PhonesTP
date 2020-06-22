package com.utn.TPFinal.service;

import com.utn.TPFinal.dto.PhoneLineByUserDto;
import com.utn.TPFinal.repository.PhoneLineRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

class PhoneLineServiceTest {

    private PhoneLineService phoneLineService;

    @Mock
    private PhoneLineRepository phoneLineRepository;

    @BeforeEach
    void setUp() {
        initMocks(this);
        phoneLineService= new PhoneLineService(phoneLineRepository);
    }

    @Test
    void addPhoneLine() {
        PhoneLineByUserDto phoneLine = new PhoneLineByUserDto(1,"mobile");
        when(phoneLineRepository.addPhoneLine(phoneLine.getUser(),phoneLine.getLineType())).thenReturn(1);
        Integer response = phoneLineService.addPhoneLine(phoneLine);

        assertNotNull(response);
        assertEquals(1,response);
        verify(phoneLineRepository,times(1)).addPhoneLine(phoneLine.getUser(),phoneLine.getLineType());
    }

    @Test
    void deletePhoneLine() {
        doNothing().when(phoneLineRepository).deletePhoneLine(any());
        phoneLineService.deletePhoneLine(any());
        verify(phoneLineRepository).deletePhoneLine(any());
    }

    @Test
    void enablePhoneLine() {
        doNothing().when(phoneLineRepository).enablePhoneLine(any());
        phoneLineService.enablePhoneLine(any());
        verify(phoneLineRepository).enablePhoneLine(any());
    }
}