package com.malfaa.pmdp.service;

import com.malfaa.pmdp.repository.MentorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class MentorServiceTest {

    @Mock
    MentorRepository mentorRepository;

    @InjectMocks
    MentorService mentorService;

    @Test
    void deveProcurarPorUmIdERetornarVerdadeiro(){

    }

}
